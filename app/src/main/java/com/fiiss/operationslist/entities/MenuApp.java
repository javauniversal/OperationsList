package com.fiiss.operationslist.entities;

import com.google.firebase.database.PropertyName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MenuApp implements Serializable {

    private Long uid;
    private String name;
    private String nameUS;

    @PropertyName("areas")
    private List<Areas> areas;

    public MenuApp() {}

    public MenuApp(Long uid, String name, String nameUS, List<Areas> areas) {
        this.uid = uid;
        this.name = name;
        this.nameUS = nameUS;
        this.areas = areas;
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

    public List<Areas> getAreas() {
        return areas;
    }

    public void setAreas(Map<String, Areas> mapa) {
        this.areas = new ArrayList<>();
        this.areas.addAll(mapa.values());
    }

}
