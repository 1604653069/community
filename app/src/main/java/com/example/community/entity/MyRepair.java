package com.example.community.entity;

import java.util.List;

public class MyRepair {

    /**
     * rid : 4028a78170773a590170776c9bd20060
     * repairPhotos : [{"pid":"4028a78170773a590170776c9bde0062","path":"http://192.168.1.106:8080/community/upload/3BBE78935EDE4212B17866C5510E8315/repair/F687A766D6EA4961B2BACDCD5E279B25.png"},{"pid":"4028a78170773a590170776c9bda0061","path":"http://192.168.1.106:8080/community/upload/3BBE78935EDE4212B17866C5510E8315/repair/881E832C46F842E792A961ED1E82FAF4.png"},{"pid":"4028a78170773a590170776c9be30063","path":"http://192.168.1.106:8080/community/upload/3BBE78935EDE4212B17866C5510E8315/repair/EE997EC5ABCC4B78A0CD7DE16B4CFCB4.png"}]
     * question : 测试数据1
     * datetime : 2020-02-24 09:45:21
     * state : 1
     */

    private String rid;
    private String question;
    private String datetime;
    private int state;
    private List<RepairPhotosBean> repairPhotos;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<RepairPhotosBean> getRepairPhotos() {
        return repairPhotos;
    }

    public void setRepairPhotos(List<RepairPhotosBean> repairPhotos) {
        this.repairPhotos = repairPhotos;
    }

    public static class RepairPhotosBean {
        /**
         * pid : 4028a78170773a590170776c9bde0062
         * path : http://192.168.1.106:8080/community/upload/3BBE78935EDE4212B17866C5510E8315/repair/F687A766D6EA4961B2BACDCD5E279B25.png
         */

        private String pid;
        private String path;

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
