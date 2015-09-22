package com.rpc.util.fmt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;

import com.rpc.util.fmt.xml.CDDATAEscapeHandler;
import com.sun.xml.txw2.output.CharacterEscapeHandler;

/**
 * JSON与XML的转换工具类
 * 
 * <pre>
 * model对象与xml互转,需要加相关注释 类名上加：
 * &#64;XmlAccessorType(XmlAccessType.FIELD)
 * &#64;XmlRootElement(name="xml") 
 * 属性上加:
 * &#64;XmlElement(name="propertyName")
 * &#64;JsonProperty("propertyName")
 * </pre>
 * 
 */
public class FormatFactory {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final TypeFactory TYPE_FACTORY;

    /**
     * 加载Jackson对象映射器
     */
    static {
        MAPPER.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        TYPE_FACTORY = MAPPER.getTypeFactory();
    }

    /**
     * 对象转XML字符串
     * 
     * @author wangxin
     * @date 2015-03-27
     * @param object
     * @return XML字符串
     */
    public static String objectToXml(Object object) {
        String result = null;
        try {
            JAXBContext jc = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // 不生成头信息
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            marshaller.setProperty(CharacterEscapeHandler.class.getName(), new CDDATAEscapeHandler());

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            marshaller.marshal(object, os);
            result = new String(os.toByteArray(), "UTF-8");
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * XML字符串转对象
     * 
     * @author wangxin
     * @date 2015-03-27
     * @param clazz
     *            对象泛型
     * @param xml
     *            XML字符串
     * @return 对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T xmlToObject(Class<T> clazz, String xml) {
        T object = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            object = (T) unmarshaller.unmarshal(new ByteArrayInputStream(xml.getBytes()));
        } catch (JAXBException e) {
            e.getMessage();
        }
        return object;
    }

    /**
     * 对象转JSON字符串
     * 
     * @author wangxin
     * @date 2015-03-27
     * @param object
     *            对象
     * @return JSON字符串
     */
    public static String objectToJson(Object object) {
        String result = null;
        try {
            result = MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * JSON字符串转对象
     * 
     * @author wangxin
     * @date 2015-03-27
     * @param clazz
     *            对象泛型
     * @param json
     *            JSON字符串
     * @return 对象
     */
    public static <T> T jsonToObject(Class<T> clazz, String json) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < json.length(); i++) {
            char ch = json.charAt(i);
            if (!Character.isHighSurrogate(ch) && !Character.isLowSurrogate(ch)) {
                sb.append(ch);
            }
        }
        json = sb.toString();
        T object = null;
        try {
            object = MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * json转List
     * 
     * @author wangxin
     * @date 2015-03-27
     * @param json
     *            json字符串
     * @param clazz
     *            List中泛型对应的Class字节码
     * @return List<T> List对象
     */
    public static <T extends Serializable> List<T> jsonToList(String json, Class<T> clazz) {
        JavaType javaType = null;
        try {
            if (StringUtils.isBlank(json))
                throw new IllegalArgumentException("json参数不能为空！");
            if (clazz == null)
                throw new IllegalArgumentException("List中泛型对应的Class字节码！");

            javaType = TYPE_FACTORY.constructCollectionType(List.class, clazz);
            return MAPPER.readValue(json, javaType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * json转Map
     * 
     * @author wangxin
     * @date 2015-03-27
     * @param json
     *            json字符串
     * @return Map<String, Object> Map对象 注：Map的key为String类型，Map中的Value为顶级父类Object， 对于Map中包含的集合类型，则转为String处理。
     */
    public static Map<String, Object> jsonToMap(String json) {
        JavaType javaType = null;
        try {
            if (StringUtils.isBlank(json))
                throw new IllegalArgumentException("json参数不能为空！");

            javaType = TYPE_FACTORY.constructMapType(Map.class, String.class, Object.class);
            Map<String, Object> resultMap = MAPPER.readValue(json, javaType);

            for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
                Object value = entry.getValue();
                if (value instanceof Collection || value instanceof Map) {
                    String valueJson = objectToJson(value);
                    entry.setValue(valueJson);
                }
            }

            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * json转Map
     * 
     * @author wangxin
     * @date 2015-03-27
     * @param json
     *            json字符串
     * @param clazz
     *            Map Value对应的Class字节码
     * @return Map<T> Map对象 注：Map的key为String类型，Map中的Value为固定的类型，并且非顶级父类Object
     */
    public static <T extends Serializable> Map<String, T> jsonToMap(String json, Class<T> clazz) {
        JavaType javaType = null;
        try {
            if (StringUtils.isBlank(json))
                throw new IllegalArgumentException("json参数不能为空！");
            if (clazz == null)
                throw new IllegalArgumentException("Map中泛型对应的Class字节码参数不能为空！");

            javaType = TYPE_FACTORY.constructMapType(Map.class, String.class, clazz);
            return MAPPER.readValue(json, javaType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * json转数组
     * 
     * @author wangxin
     * @date 2015-03-27
     * @param json
     *            json字符串
     * @param clazz
     *            数组类型对应的Class字节码
     * @return T[] 数组对象
     */
    public static <T extends Serializable> T[] jsonToArray(String json, Class<T> clazz) {
        JavaType javaType = null;
        try {
            if (StringUtils.isBlank(json))
                throw new IllegalArgumentException("json参数不能为空！");
            if (clazz == null)
                throw new IllegalArgumentException("数组类型对应的Class字节码参数不能为空！");

            javaType = TYPE_FACTORY.constructArrayType(clazz);
            return MAPPER.readValue(json, javaType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
