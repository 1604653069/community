package com.example.community.entity;

import java.util.List;

public class Favorite {

    /**
     * favorites : [{"fiid":"4028a7817032ea2e017032eab1ce0001","product":{"isHot":0,"marketPrice":89,"pImage":"http://192.168.1.106:8080/community/product/skirt/1463385771450.jpg","pdate":1575475200000,"pdesc":"韩都衣舍2019秋冬新款超仙女装气质女神范衣服假两件碎花连衣裙緈","pflag":0,"pid":"4FB640D1EF7D40629C872218E3E2E24B","pname":"【韩都衣舍2019秋冬新款超仙女装气质女神范衣服假两件碎花连衣裙緈】","shopPrice":99,"type":3},"type":0},{"fiid":"4028a7817032ea2e017032eb17150003","product":{"isHot":0,"marketPrice":88,"pImage":"http://192.168.1.106:8080/community/product/skirt/1464776087422.jpg","pdate":1575302400000,"pdesc":"欧洲站2019连衣裙女秋冬轻熟风气质洋气针织法式韩系穿搭长连衣裙","pflag":0,"pid":"9C2ACCABA00746C597C875A132132193","pname":"【欧洲站2019连衣裙女秋冬轻熟风气质洋气针织法式韩系穿搭长连衣裙】","shopPrice":99,"type":3},"type":0},{"fiid":"4028a7817032ea2e017032eb17150004","house":{"address":"建安·王庄·紫阳新园","area":"32","date":1576771200000,"detailed":"世欧广场紫阳新园一室一卫仅800单身公寓全新装修","hid":"04A0758D0DC8437DAE68B1FBBD456A89","houseImgs":[{"iid":"583523B3AFC345118AC16FC3C72E2000","path":"http://192.168.1.106:8080/community/house/2019-12-17_21.41.16.923.jpg"},{"iid":"F606C33C6C6D4DFB8EE3B76B785F7AD0","path":"http://192.168.1.106:8080/community/house/2019-12-17_21.41.13.997.jpg"},{"iid":"E3E4A6C7388045A9A907AE8EBFE517A6","path":"http://192.168.1.106:8080/community/house/2019-12-17_21.41.10.090.jpg"},{"iid":"99116E9A667B4F54BFB1C2D72239F074","path":"http://192.168.1.106:8080/community/house/2019-12-17_21.41.11.975.jpg"}],"rent":1000,"title":"一室一卫仅800单身公寓"},"type":0},{"fiid":"4028a7817032e753017032e88a220000","product":{"isHot":0,"marketPrice":77,"pImage":"http://192.168.1.106:8080/community/product/skirt/1465268743242.jpg","pdate":1575129600000,"pdesc":"卡米兰羊毛连衣裙女2019秋冬新款中长款毛衣打底裙长袖针织裙子","pflag":0,"pid":"39204111877D4E09AF4BF0B0CE8FCB63","pname":"【卡米兰羊毛连衣裙女2019秋冬新款中长款毛衣打底裙长袖针织裙子】","shopPrice":88,"type":3},"type":0},{"fiid":"4028a7817032ea2e017032eacb1c0002","product":{"isHot":0,"marketPrice":55,"pImage":"http://192.168.1.106:8080/community/product/skirt/1476170715116.jpg","pdate":1575475200000,"pdesc":"纯羊毛针织连衣裙秋冬穿搭女装2019新宽松时尚中长款毛衣裙子过膝","pflag":0,"pid":"7399BC172A334A3C94ACDE9AC7BD73B8","pname":"【纯羊毛针织连衣裙秋冬穿搭女装2019新宽松时尚中长款毛衣裙子过膝】","shopPrice":66,"type":3},"type":0},{"fiid":"4028a7817032ea2e017032ea8bcd0000","product":{"isHot":0,"marketPrice":78,"pImage":"http://192.168.1.106:8080/community/product/skirt/1457504361484.jpg","pdate":1575388800000,"pdesc":"三彩2019早秋冬季新款内搭短连衣裙收腰修身长袖赫本针织小黑裙女","pflag":0,"pid":"4EC762C07981457494876FAD45A7B208","pname":"【三彩2019早秋冬季新款内搭短连衣裙收腰修身长袖赫本针织小黑裙女】","shopPrice":89,"type":3},"type":0}]
     * fid : 4028a7817032dd31017032df79540000
     */

    private String fid;
    private List<FavoritesBean> favorites;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public List<FavoritesBean> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<FavoritesBean> favorites) {
        this.favorites = favorites;
    }

    public static class FavoritesBean {
        /**
         * fiid : 4028a7817032ea2e017032eab1ce0001
         * product : {"isHot":0,"marketPrice":89,"pImage":"http://192.168.1.106:8080/community/product/skirt/1463385771450.jpg","pdate":1575475200000,"pdesc":"韩都衣舍2019秋冬新款超仙女装气质女神范衣服假两件碎花连衣裙緈","pflag":0,"pid":"4FB640D1EF7D40629C872218E3E2E24B","pname":"【韩都衣舍2019秋冬新款超仙女装气质女神范衣服假两件碎花连衣裙緈】","shopPrice":99,"type":3}
         * type : 0
         * house : {"address":"建安·王庄·紫阳新园","area":"32","date":1576771200000,"detailed":"世欧广场紫阳新园一室一卫仅800单身公寓全新装修","hid":"04A0758D0DC8437DAE68B1FBBD456A89","houseImgs":[{"iid":"583523B3AFC345118AC16FC3C72E2000","path":"http://192.168.1.106:8080/community/house/2019-12-17_21.41.16.923.jpg"},{"iid":"F606C33C6C6D4DFB8EE3B76B785F7AD0","path":"http://192.168.1.106:8080/community/house/2019-12-17_21.41.13.997.jpg"},{"iid":"E3E4A6C7388045A9A907AE8EBFE517A6","path":"http://192.168.1.106:8080/community/house/2019-12-17_21.41.10.090.jpg"},{"iid":"99116E9A667B4F54BFB1C2D72239F074","path":"http://192.168.1.106:8080/community/house/2019-12-17_21.41.11.975.jpg"}],"rent":1000,"title":"一室一卫仅800单身公寓"}
         */

        private String fiid;
        private ProductBean product;
        private int type;
        private HouseBean house;

        public String getFiid() {
            return fiid;
        }

        public void setFiid(String fiid) {
            this.fiid = fiid;
        }

        public ProductBean getProduct() {
            return product;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public HouseBean getHouse() {
            return house;
        }

        public void setHouse(HouseBean house) {
            this.house = house;
        }

        public static class ProductBean {
            /**
             * isHot : 0
             * marketPrice : 89
             * pImage : http://192.168.1.106:8080/community/product/skirt/1463385771450.jpg
             * pdate : 1575475200000
             * pdesc : 韩都衣舍2019秋冬新款超仙女装气质女神范衣服假两件碎花连衣裙緈
             * pflag : 0
             * pid : 4FB640D1EF7D40629C872218E3E2E24B
             * pname : 【韩都衣舍2019秋冬新款超仙女装气质女神范衣服假两件碎花连衣裙緈】
             * shopPrice : 99
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
        }

        public static class HouseBean {
            /**
             * address : 建安·王庄·紫阳新园
             * area : 32
             * date : 1576771200000
             * detailed : 世欧广场紫阳新园一室一卫仅800单身公寓全新装修
             * hid : 04A0758D0DC8437DAE68B1FBBD456A89
             * houseImgs : [{"iid":"583523B3AFC345118AC16FC3C72E2000","path":"http://192.168.1.106:8080/community/house/2019-12-17_21.41.16.923.jpg"},{"iid":"F606C33C6C6D4DFB8EE3B76B785F7AD0","path":"http://192.168.1.106:8080/community/house/2019-12-17_21.41.13.997.jpg"},{"iid":"E3E4A6C7388045A9A907AE8EBFE517A6","path":"http://192.168.1.106:8080/community/house/2019-12-17_21.41.10.090.jpg"},{"iid":"99116E9A667B4F54BFB1C2D72239F074","path":"http://192.168.1.106:8080/community/house/2019-12-17_21.41.11.975.jpg"}]
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
            private List<House.HouseImgsBean> houseImgs;

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

            public List<House.HouseImgsBean> getHouseImgs() {
                return houseImgs;
            }

            public void setHouseImgs(List<House.HouseImgsBean> houseImgs) {
                this.houseImgs = houseImgs;
            }

//            public static class HouseImgsBean {
//                /**
//                 * iid : 583523B3AFC345118AC16FC3C72E2000
//                 * path : http://192.168.1.106:8080/community/house/2019-12-17_21.41.16.923.jpg
//                 */
//
//                private String iid;
//                private String path;
//
//                public String getIid() {
//                    return iid;
//                }
//
//                public void setIid(String iid) {
//                    this.iid = iid;
//                }
//
//                public String getPath() {
//                    return path;
//                }
//
//                public void setPath(String path) {
//                    this.path = path;
//                }
//            }
        }
    }
}
