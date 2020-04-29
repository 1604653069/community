package com.example.community.entity;

import android.net.Uri;

/*报事维修图片实体类*/
public class Images {
    private int type;
    private Uri uri;
    private int id;
    public Images() {
    }
    public Images(int type, Uri uri, int id) {
        this.type = type;
        this.uri = uri;
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
