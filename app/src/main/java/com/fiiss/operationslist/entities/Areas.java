package com.fiiss.operationslist.entities;

import java.io.Serializable;

public class Areas implements Serializable {

    private Long uid;
    private String name;
    private String nameUS;

    public Areas() {
    }

    public Areas(Long uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameUS() {
        return nameUS;
    }

    public void setNameUS(String nameUS) {
        this.nameUS = nameUS;
    }
}
