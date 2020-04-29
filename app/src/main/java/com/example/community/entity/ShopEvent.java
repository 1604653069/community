package com.example.community.entity;

public class ShopEvent {
    private Shop shop;
    public ShopEvent() {
    }
    public ShopEvent(Shop shop) {
        this.shop = shop;
    }
    public Shop getShop() {
        return shop;
    }
    public void setShop(Shop shop) {
        this.shop = shop;
    }
}

