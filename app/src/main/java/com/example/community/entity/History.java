package com.example.community.entity;

public class History {


    /**
     * balance : 10
     * hid : 4028a78170399321017039936d2d0000
     * money : 140
     * time : 1581436800000
     */

    private int balance;
    private String hid;
    private int money;
    private String time;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
