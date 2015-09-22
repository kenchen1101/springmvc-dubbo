package com.rpc.common.fmt.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CDDATAAdapter extends XmlAdapter<String, String> {

    @Override
    public String unmarshal(String v) throws Exception {
        return v;
    }

    @Override
    public String marshal(String v) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("<![CDATA[").append(v).append("]]>");
        return sb.toString();
    }

}
