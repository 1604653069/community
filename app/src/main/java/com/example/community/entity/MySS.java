package com.example.community.entity;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/*说说实体类*/
public class MySS {
    /*发表说说人*/
    private String name;
    /*发表和说说的内容*/
    private String content;
    /*发表说说的图片uri地址*/
    private List<Uri> uris = new ArrayList<>();
    /*发表说说的时间*/
    private String time;

    public MySS() {
    }

    public MySS(String name, String content, List<Uri> uris, String time) {
        this.name = name;
        this.content = content;
        this.uris = uris;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Uri> getUris() {
        return uris;
    }

    public void setUris(List<Uri> uris) {
        this.uris = uris;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
