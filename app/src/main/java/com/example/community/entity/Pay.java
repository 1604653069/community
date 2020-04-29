package com.example.community.entity;

import java.util.List;

public class Pay {

    /**
     * pays : [{"detail":"11月份物业费用缴纳","endDate":"2020-03-08","money":123,"pid":"4028a78170a938890170a93a98db0000","startDate":"2020-03-06","type":2},{"commitDate":"2020-03-05","detail":"缴纳水费","endDate":"2020-03-20","money":123,"pid":"4028a78170a970ec0170a980af490000","startDate":"2020-03-05","type":0},{"commitDate":"2020-03-05","detail":"缴纳物业费","endDate":"2020-03-23","money":321,"pid":"4028a78170a970ec0170a980f2670001","startDate":"2020-03-05","type":2},{"commitDate":"2020-03-05","detail":"缴纳电费","endDate":"2020-03-20","money":213,"pid":"4028a78170a970ec0170a9817f090002","startDate":"2020-03-05","type":1}]
     * success : true
     */

    private boolean success;
    private List<PaysBean> pays;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<PaysBean> getPays() {
        return pays;
    }

    public void setPays(List<PaysBean> pays) {
        this.pays = pays;
    }

    public static class PaysBean {
        /**
         * detail : 11月份物业费用缴纳
         * endDate : 2020-03-08
         * money : 123
         * pid : 4028a78170a938890170a93a98db0000
         * startDate : 2020-03-06
         * type : 2
         * commitDate : 2020-03-05
         */

        private String detail;
        private String endDate;
        private int money;
        private String pid;
        private String startDate;
        private int type;
        private String commitDate;
        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCommitDate() {
            return commitDate;
        }

        public void setCommitDate(String commitDate) {
            this.commitDate = commitDate;
        }
    }
}
