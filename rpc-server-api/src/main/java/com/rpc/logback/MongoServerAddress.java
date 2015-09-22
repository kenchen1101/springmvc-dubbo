package com.rpc.logback;

public class MongoServerAddress {

    private String address = null;

    private int port = 0;

    public void setAddress(String address) {
        this.address = address;
    }

    String getAddress() {
        return address;
    }

    public void setPort(int port) {
        this.port = port;
    }

    int getPort() {
        return port;
    }

    boolean isValid() {
        return address != null && port > 0;
    }
}
