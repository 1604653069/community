package com.example.community.entity;

/*水费的实体*/
public class WaterPay {
    /*缴费人的名称*/
    private String name;
    /*缴费的时间*/
    private String date;
    /*缴费的金额*/
    private double money;
    /*缴费的类型 0:水费 1:电费 2:物业费*/
    private int type;
    public WaterPay() {
    }
    public WaterPay(String name, String date, double money,int type) {
        this.name = name;
        this.date = date;
        this.money = money;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
}
