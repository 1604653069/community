package com.example.community.entity;

/*图标类*/
public class Icon {
    private int id;
    private String name;

    public Icon() {
    }

    public Icon(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
