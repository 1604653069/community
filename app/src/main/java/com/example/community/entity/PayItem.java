package com.example.community.entity;

public class PayItem {

    /**
     * payItem : {"iid":"4028a78170aa97b80170aa982b0c0000","money":0,"state":0}
     */

    private PayItemBean payItem;

    public PayItemBean getPayItem() {
        return payItem;
    }

    public void setPayItem(PayItemBean payItem) {
        this.payItem = payItem;
    }

    public static class PayItemBean {
        /**
         * iid : 4028a78170aa97b80170aa982b0c0000
         * money : 0
         * state : 0
         */

        private String iid;
        private int money;
        private int state;

        public String getIid() {
            return iid;
        }

        public void setIid(String iid) {
            this.iid = iid;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
