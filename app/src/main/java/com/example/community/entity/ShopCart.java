package com.example.community.entity;

import java.util.List;

/*购物车商品类*/
public class ShopCart {

    /**
     * cid : 297e40a06efe2f7a016efe2fe6880000
     * items : [{"iid":"297e40a06efe2f7a016efe3059bd0002","product":{"isHot":0,"marketPrice":26,"pImage":"http://10.101.45.12:8080/community/product/clothes/1453477900441.jpg","pdate":1575648000000,"pdesc":"JUZUI/玖姿官方旗舰店2019夏季新款V领套头碎花印花雪纺衫女上衣","pflag":0,"pid":"1F68E7629A81404B945544B1C9464F69","pname":"【JUZUI/玖姿官方旗舰店2019夏季新款V领套头碎花印花雪纺衫女上衣】","shopPrice":35,"type":1},"quantity":1,"total":35},{"iid":"297e40a06efe2f7a016efe2fe6c10001","product":{"isHot":0,"marketPrice":56,"pImage":"http://10.101.45.12:8080/community/product/clothes/1469173640233.jpg","pdate":1575129600000,"pdesc":"【三彩2019夏季新款设计感喇叭袖白衬衫韩版衬衣很仙的上衣女】","pflag":0,"pid":"3EEEE1E01F4C459499D06C46A7F0FE1A","pname":"【三彩2019夏季新款设计感喇叭袖白衬衫韩版衬衣很仙的上衣女】","shopPrice":67,"type":1},"quantity":1,"total":67}]
     */
    private String cid;
    private List<ItemsBean> items;

    public String getCid() {
        return cid;
    }
    public void setCid(String cid) {
        this.cid = cid;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * iid : 297e40a06efe2f7a016efe3059bd0002
         * product : {"isHot":0,"marketPrice":26,"pImage":"http://10.101.45.12:8080/community/product/clothes/1453477900441.jpg","pdate":1575648000000,"pdesc":"JUZUI/玖姿官方旗舰店2019夏季新款V领套头碎花印花雪纺衫女上衣","pflag":0,"pid":"1F68E7629A81404B945544B1C9464F69","pname":"【JUZUI/玖姿官方旗舰店2019夏季新款V领套头碎花印花雪纺衫女上衣】","shopPrice":35,"type":1}
         * quantity : 1
         * total : 35
         */

        private String iid;
        private ProductBean product;
        private int quantity;
        private int total;
        private boolean isSelected = false;
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

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public static class ProductBean {
            /**
             * isHot : 0
             * marketPrice : 26
             * pImage : http://10.101.45.12:8080/community/product/clothes/1453477900441.jpg
             * pdate : 1575648000000
             * pdesc : JUZUI/玖姿官方旗舰店2019夏季新款V领套头碎花印花雪纺衫女上衣
             * pflag : 0
             * pid : 1F68E7629A81404B945544B1C9464F69
             * pname : 【JUZUI/玖姿官方旗舰店2019夏季新款V领套头碎花印花雪纺衫女上衣】
             * shopPrice : 35
             * type : 1
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
