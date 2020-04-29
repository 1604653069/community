package com.example.community.entity;

import java.util.List;

/*初始界面商品信息类*/
public class StartUpShop {
    private List<CommandBean> command;
    private List<HotBean> hot;
    public List<CommandBean> getCommand() {
        return command;
    }

    public void setCommand(List<CommandBean> command) {
        this.command = command;
    }

    public List<HotBean> getHot() {
        return hot;
    }

    public void setHot(List<HotBean> hot) {
        this.hot = hot;
    }

    public static class CommandBean {
        /**
         * isHot : 0
         * marketPrice : 77
         * pImage : http://10.101.45.12:8080/community/product/Bottling/1465268743242.jpg
         * pdate : 1575129600000
         * pdesc : 卡米兰羊毛连衣裙女2019秋冬新款中长款毛衣打底裙长袖针织裙子
         * pflag : 0
         * pid : 39204111877D4E09AF4BF0B0CE8FCB63
         * pname : 【卡米兰羊毛连衣裙女2019秋冬新款中长款毛衣打底裙长袖针织裙子】
         * shopPrice : 88
         * type : 3
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
        @Override
        public String toString() {
            return "CommandBean{" +
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

    public static class HotBean {
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
        private String test;

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

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

        public String getTest() {
            return test;
        }

        public void setTest(String test) {
            this.test = test;
        }
    }

}
