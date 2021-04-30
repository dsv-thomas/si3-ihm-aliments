package com.dsv.td1.si3_ihm_aliments;

import java.util.UUID;

public class User {
    private String name;
    private UUID uuid;

    public User(String name) {
        this.name = name;
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
