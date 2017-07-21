package com.kteam.cardenglishgamer.model;

/**
 * Created by Bloomkk on 2017/7/12.
 */

public class WrongSubject {
    private int ID;
    private String q_id;
    private int error_num;

    public int getError_num() {
        return error_num;
    }

    public int getID() {
        return ID;
    }

    public String getQ_id() {
        return q_id;
    }

    public void setError_num(int error_num) {
        this.error_num = error_num;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setQ_id(String q_id) {
        this.q_id = q_id;
    }
}
