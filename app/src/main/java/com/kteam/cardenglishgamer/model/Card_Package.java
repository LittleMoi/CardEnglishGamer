package com.kteam.cardenglishgamer.model;

/**
 * Created by Bloomkk on 2017/7/12.
 */

public class Card_Package {
    private int ID;
    private int card_id;
    private int  card_attri_level;
    private int card_attri_exp;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    public void setCard_attri_level(int card_attri_level) {
        this.card_attri_level = card_attri_level;
    }

    public void setCard_attri_exp(int card_attri_exp) {
        this.card_attri_exp = card_attri_exp;
    }

    public int getCard_attri_exp() {
        return card_attri_exp;
    }

    public int getCard_attri_level() {
        return card_attri_level;
    }

    public int getCard_id() {
        return card_id;
    }

    public int getID() {
        return ID;
    }

}
