package com.test.assignment01.domain;

import java.util.UUID;

public class ConnectionGroupDomain {

    private String name;
    private String id;

    public ConnectionGroupDomain(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }
}
