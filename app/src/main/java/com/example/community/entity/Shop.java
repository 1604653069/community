package com.example.community.entity;

public class Shop {
    /**
     * isHot : 1
     * marketPrice : 21
     * pImage : http://10.101.45.12:8080/community/product/baobao/1447732345223.jpg
     * pdate : 1575475200000
     * pdesc : 2019新款CK2-50680795-1翻盖手提单肩包小方包女包
     * pflag : 0
     * pid : 0E8B2539FDCE42FDBDD12FFF8BBDC859
     * pname : 【翻盖手提单肩包小方包女包】
     * shopPrice : 29
     * type : 0
     */

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

    public String getPImage() {
        return pImage;
    }

    public void setPImage(String pImage) {
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
}
