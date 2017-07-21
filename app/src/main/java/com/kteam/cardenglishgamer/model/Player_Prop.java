package com.kteam.cardenglishgamer.model;

/**
 * Created by Bloomkk on 2017/7/12.
 */

public class Player_Prop {
    private String ID;
    private String prop_icon;
    private int prop_num;
    private String prop_description;

    public int getProp_num() {
        return prop_num;
    }

    public String getID() {
        return ID;
    }

    public String getProp_description() {
        return prop_description;
    }

    public String getProp_icon() {
        return prop_icon;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setProp_description(String prop_description) {
        this.prop_description = prop_description;
    }

    public void setProp_icon(String prop_icon) {
        this.prop_icon = prop_icon;
    }

    public void setProp_num(int prop_num) {
        this.prop_num = prop_num;
    }
}
