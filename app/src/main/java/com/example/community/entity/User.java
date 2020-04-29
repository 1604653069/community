package com.example.community.entity;

import java.util.List;

/*用户类*/
public class User {

    /**
     * addreses : [{"address":"内蒙古-呼和浩特市-新城区","aid":"4028a781701fae7c01701fb894fe0000","name":"李四","phone":"13222222222","statues":0},{"address":"福建省-建瓯市-吉阳镇大夫村","aid":"350F90395E9344C0ADFF05C73561500C","name":"张三","phone":"13225971501","statues":1}]
     * address : 武夷山武夷小区
     * name : 管理员
     * password : admin
     * sex : 男
     * state : 1
     * tel : 110
     * type : 0
     * uid : 3BBE78935EDE4212B17866C5510E8315
     * username : admin
     */
    //住址
    private String address;
    private String name;
    private String password;
    private String sex;
    private String state;
    private String tel;
    private String type;
    private String uid;
    private String username;
    //收货地址
    private List<AddresesBean> addreses;

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

    public List<AddresesBean> getAddreses() {
        return addreses;
    }

    public void setAddreses(List<AddresesBean> addreses) {
        this.addreses = addreses;
    }

    public static class AddresesBean {
        /**
         * address : 内蒙古-呼和浩特市-新城区
         * aid : 4028a781701fae7c01701fb894fe0000
         * name : 李四
         * phone : 13222222222
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
}
