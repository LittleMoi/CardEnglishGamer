package com.kteam.cardenglishgamer.model;

/**
 * Created by Bloomkk on 2017/7/12.
 */

public class Card_SkillAttributes {
    private int card_skill_id;
    private String card_skill_description;
    private int card_skill_damage_magn;
    private String card_skill_model;
    private int card_skill_damage_type;

    public int getCard_skill_damage_magn() {
        return card_skill_damage_magn;
    }

    public int getCard_skill_damage_type() {
        return card_skill_damage_type;
    }

    public int getCard_skill_id() {
        return card_skill_id;
    }

    public String getCard_skill_description() {
        return card_skill_description;
    }

    public String getCard_skill_model() {
        return card_skill_model;
    }

    public void setCard_skill_damage_magn(int card_skill_damage_magn) {
        this.card_skill_damage_magn = card_skill_damage_magn;
    }

    public void setCard_skill_damage_type(int card_skill_damage_type) {
        this.card_skill_damage_type = card_skill_damage_type;
    }

    public void setCard_skill_description(String card_skill_description) {
        this.card_skill_description = card_skill_description;
    }

    public void setCard_skill_id(int card_skill_id) {
        this.card_skill_id = card_skill_id;
    }

    public void setCard_skill_model(String card_skill_model) {
        this.card_skill_model = card_skill_model;
    }
}
