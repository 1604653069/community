package com.example.community.entity;

import java.io.Serializable;

public class GoodInfo implements Serializable {
    private int isHot;
    private int marketPrice;
    private String pImage;
    private long pdate;
    private String pdesc;
    private int pflag;
    private String pid;
    private String pname;
    private int shopPrice;
    private int type;

    public int getIsHot() {
        return isHot;
    }

    public void setIsHot(int isHot) {
        this.isHot = isHot;
    }

    public int getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(int marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getpImage() {
        return pImage;
    }

    public void setpImage(String pImage) {
        this.pImage = pImage;
    }

    public long getPdate() {
        return pdate;
    }

    public void setPdate(long pdate) {
        this.pdate = pdate;
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }

    public int getPflag() {
        return pflag;
    }

    public void setPflag(int pflag) {
        this.pflag = pflag;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(int shopPrice) {
        this.shopPrice = shopPrice;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return "GoodInfo{" +
                "isHot=" + isHot +
                ", marketPrice=" + marketPrice +
                ", pImage='" + pImage + '\'' +
                ", pdate=" + pdate +
                ", pdesc='" + pdesc + '\'' +
                ", pflag=" + pflag +
                ", pid='" + pid + '\'' +
                ", pname='" + pname + '\'' +
                ", shopPrice=" + shopPrice +
                ", type=" + type +
                '}';
    }
}
