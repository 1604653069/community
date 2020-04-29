package com.example.community.entity;

public class MineEntry {
    private int imgId;
    private String name;
    public MineEntry(){

    }
    public MineEntry(int imgId,String name){
        this.imgId = imgId;
        this.name = name;
    }
    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

