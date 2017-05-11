package com.beforever.model;

/**
 * Created by BeForever on 17/5/11.
 */
public class User {
    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    private String getDescription() {
        return "This is" + name;
    }

}
