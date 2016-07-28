package com.ljc.projectmodel.beans;

import java.io.Serializable;

/**
 * Created by lijiacheng on 16/6/29.
 */
public class LinkManBean implements Serializable {
    protected String title;
    protected String name;
    protected String phone;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
