package com.kteam.cardenglishgamer.model;

/**
 * Created by Bloomkk on 2017/7/12.
 */

public class Card_BasicalAttributes {
    private String ID;
    private String card_name;
    private int card_type;
    private String card_image;
    private int card_attri_attack;
    private int card_attri_defense;
    private int card_attri_hp;
    private int card_attri_quailty;

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getCard_attri_attack() {
        return card_attri_attack;
    }

    public int getCard_attri_defense() {
        return card_attri_defense;
    }

    public int getCard_attri_hp() {
        return card_attri_hp;
    }

    public int getCard_attri_quailty() {
        return card_attri_quailty;
    }

    public int getCard_type() {
        return card_type;
    }

    public String getCard_image() {
        return card_image;
    }

    public String getCard_name() {
        return card_name;
    }

    public String getID() {
        return ID;
    }

    public void setCard_attri_attack(int card_attri_attack) {
        this.card_attri_attack = card_attri_attack;
    }

    public void setCard_attri_defense(int card_attri_defense) {
        this.card_attri_defense = card_attri_defense;
    }

    public void setCard_attri_hp(int card_attri_hp) {
        this.card_attri_hp = card_attri_hp;
    }

    public void setCard_attri_quailty(int card_attri_quailty) {
        this.card_attri_quailty = card_attri_quailty;
    }

    public void setCard_image(String card_image) {
        this.card_image = card_image;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public void setCard_type(int card_type) {
        this.card_type = card_type;
    }

}
