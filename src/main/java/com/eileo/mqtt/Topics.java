package com.eileo.mqtt;

public enum Topics {

    Example("zfc/messagaes", 2);

    Topics(String name, int qos) {
        this.name = name;
        this.qos = qos;
    }

    public String getName() {
        return name;
    }

    public int getQos() {
        return qos;
    }

    private final String name;

    private final int qos;

}
