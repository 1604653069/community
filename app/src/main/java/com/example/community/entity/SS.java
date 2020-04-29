package com.example.community.entity;

import java.util.List;

public class SS {

    /**
     * sid : 297e40a06ec18067016ec18130150000
     * ssContent : 这是我说说的测试数据...
     * ssImgs : [{"iid":"297e40a06ec18067016ec18130320002","path":"http://10.101.45.12:8080/community/upload/3BBE78935EDE4212B17866C5510E8315/ss/297e40a06ec18067016ec18130150000/99CC3FDF24A648A7A4DA4E7B6CA58110.png"},{"iid":"297e40a06ec18067016ec181302d0001","path":"http://10.101.45.12:8080/community/upload/3BBE78935EDE4212B17866C5510E8315/ss/297e40a06ec18067016ec18130150000/FB5CAAEEC5484D0E839D80481F407E85.png"}]
     * time : 2019-12-01 00:00:00
     * user : {"address":"武夷山武夷小区","name":"管理员","password":"admin","repairs":[],"sex":"男","state":"1","tel":"110","type":"0","uid":"3BBE78935EDE4212B17866C5510E8315","username":"admin"}
     */
    private String sid;
    private String ssContent;
    private String time;
    private UserBean user;
    private List<SsImgsBean> ssImgs;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSsContent() {
        return ssContent;
    }

    public void setSsContent(String ssContent) {
        this.ssContent = ssContent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<SsImgsBean> getSsImgs() {
        return ssImgs;
    }

    public void setSsImgs(List<SsImgsBean> ssImgs) {
        this.ssImgs = ssImgs;
    }

    public static class UserBean {
        /**
         * address : 武夷山武夷小区
         * name : 管理员
         * password : admin
         * repairs : []
         * sex : 男
         * state : 1
         * tel : 110
         * type : 0
         * uid : 3BBE78935EDE4212B17866C5510E8315
         * username : admin
         */

        private String address;
        private String name;
        private String password;
        private String sex;
        private String state;
        private String tel;
        private String type;
        private String uid;
        private String username;
        private List<?> repairs;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<?> getRepairs() {
            return repairs;
        }

        public void setRepairs(List<?> repairs) {
            this.repairs = repairs;
        }
    }

    public static class SsImgsBean {
        /**
         * iid : 297e40a06ec18067016ec18130320002
         * path : http://10.101.45.12:8080/community/upload/3BBE78935EDE4212B17866C5510E8315/ss/297e40a06ec18067016ec18130150000/99CC3FDF24A648A7A4DA4E7B6CA58110.png
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
