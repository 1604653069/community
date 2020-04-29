package com.example.community.entity;

import java.io.Serializable;
import java.util.List;

public class House implements Serializable {

    /**
     * address : 建安·王庄·紫阳新园
     * area : 32
     * date : 1576771200000
     * detailed : 世欧广场紫阳新园一室一卫仅800单身公寓全新装修
     * hid : 04A0758D0DC8437DAE68B1FBBD456A89
     * houseImgs : [{"iid":"583523B3AFC345118AC16FC3C72E2000","path":"http://10.101.45.12/community/house/2019-12-17_21.41.16.923.jpg"},{"iid":"F606C33C6C6D4DFB8EE3B76B785F7AD0","path":"http://10.101.45.12/community/house/2019-12-17_21.41.13.997.jpg"},{"iid":"E3E4A6C7388045A9A907AE8EBFE517A6","path":"http://10.101.45.12/community/house/2019-12-17_21.41.10.090.jpg"},{"iid":"99116E9A667B4F54BFB1C2D72239F074","path":"http://10.101.45.12/community/house/2019-12-17_21.41.11.975.jpg"}]
     * rent : 1000
     * title : 一室一卫仅800单身公寓
     */

    private String address;
    private String area;
    private long date;
    private String detailed;
    private String hid;
    private int rent;
    private String title;
    private List<HouseImgsBean> houseImgs;
    private int type;
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDetailed() {
        return detailed;
    }

    public void setDetailed(String detailed) {
        this.detailed = detailed;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<HouseImgsBean> getHouseImgs() {
        return houseImgs;
    }

    public void setHouseImgs(List<HouseImgsBean> houseImgs) {
        this.houseImgs = houseImgs;
    }

    public static class HouseImgsBean implements Serializable{
        /**
         * iid : 583523B3AFC345118AC16FC3C72E2000
         * path : http://10.101.45.12/community/house/2019-12-17_21.41.16.923.jpg
         */

        private String iid;
        private String path;

        public String getIid() {
            return iid;
        }

        public void setIid(String iid) {
            this.iid = iid;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
