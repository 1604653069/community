package com.example.community.entity;

public class OrderIcon {
    private int imgId;
    private String name;
    private int numberFk;
    private int numberSk;

    public OrderIcon() {
    }

    public OrderIcon(int imgId, String name, int numberFk, int numberSk) {
        this.imgId = imgId;
        this.name = name;
        this.numberFk = numberFk;
        this.numberSk = numberSk;
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

    public int getNumberFk() {
        return numberFk;
    }

    public void setNumberFk(int numberFk) {
        this.numberFk = numberFk;
    }

    public int getNumberSk() {
        return numberSk;
    }

    public void setNumberSk(int numberSk) {
        this.numberSk = numberSk;
    }
}
