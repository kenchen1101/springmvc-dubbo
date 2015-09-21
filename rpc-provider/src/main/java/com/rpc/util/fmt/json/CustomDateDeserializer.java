package com.rpc.util.fmt.json;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

/**
 * 
 * @author Vincent.Wang
 *
 */
public class CustomDateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        Long time = parser.getLongValue();
        Date createTime = new Date(time * 1000);
        return createTime;
    }

}
