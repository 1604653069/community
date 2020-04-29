package com.example.community.entity;

import java.util.List;

public class Order {

    /**
     * address : {"address":"福建省-建瓯市-吉阳镇大夫村","aid":"350F90395E9344C0ADFF05C73561500C","name":"张三","phone":"13225971501","statues":0}
     * oid : 4028a781704df45101704df496650000
     * orderItems : [{"count":0,"iid":"4028a781704df45101704df4966e0001","product":{"isHot":0,"marketPrice":88,"pImage":"http://192.168.1.106:8080/community/product/Bottling/1462352934101.jpg","pdate":1575302400000,"pdesc":"TeenieWeenie小熊女休闲宽松A字短裤TTTH74V60W","pflag":0,"pid":"A23C1E4FEE6447F5A429386D494D9159","pname":"【TeenieWeenie小熊女休闲宽松A字短裤TTTH74V60W】","shopPrice":99,"type":2}}]
     * state : 1
     * total : 99
     */

    private AddressBean address;
    private String oid;
    private int state;
    private int total;
    private List<OrderItemsBean> orderItems;

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<OrderItemsBean> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemsBean> orderItems) {
        this.orderItems = orderItems;
    }

    public static class AddressBean {
        /**
         * address : 福建省-建瓯市-吉阳镇大夫村
         * aid : 350F90395E9344C0ADFF05C73561500C
         * name : 张三
         * phone : 13225971501
         * statues : 0
         */

        private String address;
        private String aid;
        private String name;
        private String phone;
        private int statues;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getStatues() {
            return statues;
        }

        public void setStatues(int statues) {
            this.statues = statues;
        }
    }

    public static class OrderItemsBean {
        /**
         * count : 0
         * iid : 4028a781704df45101704df4966e0001
         * product : {"isHot":0,"marketPrice":88,"pImage":"http://192.168.1.106:8080/community/product/Bottling/1462352934101.jpg","pdate":1575302400000,"pdesc":"TeenieWeenie小熊女休闲宽松A字短裤TTTH74V60W","pflag":0,"pid":"A23C1E4FEE6447F5A429386D494D9159","pname":"【TeenieWeenie小熊女休闲宽松A字短裤TTTH74V60W】","shopPrice":99,"type":2}
         */

        private int count;
        private String iid;
        private ProductBean product;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getIid() {
            return iid;
        }

        public void setIid(String iid) {
            this.iid = iid;
        }

        public ProductBean getProduct() {
            return product;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public static class ProductBean {
            /**
             * isHot : 0
             * marketPrice : 88
             * pImage : http://192.168.1.106:8080/community/product/Bottling/1462352934101.jpg
             * pdate : 1575302400000
             * pdesc : TeenieWeenie小熊女休闲宽松A字短裤TTTH74V60W
             * pflag : 0
             * pid : A23C1E4FEE6447F5A429386D494D9159
             * pname : 【TeenieWeenie小熊女休闲宽松A字短裤TTTH74V60W】
             * shopPrice : 99
             * type : 2
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
    }
}
