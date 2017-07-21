package com.kteam.cardenglishgamer.model;

/**
 * Created by Bloomkk on 2017/7/12.
 */

public class SigningUp {
    private String ID;
    private String player_name;
    private int account_level;
    private int account_exp;
    private int money;
    public String getID(){
        return ID;
    }
    public void setID(String ID){
        this.ID=ID;
    }
    public String getPlayer_name(){
        return player_name;
    }
    public void setPlayer_name(String player_name){
        this.player_name=player_name;
    }
    public int getAccount_level(){
        return account_level;
    }
    public void setAccount_level(int account_level){
        this.account_level=account_level;
    }
    public int getAccount_exp(){
        return account_exp;
    }
    public void setAccount_exp(int account_exp){
        this.account_exp=account_exp;
    }
    public int getMoney(){
        return money;
    }
    public void setMoney(int money){
        this.money=money;
    }
}
