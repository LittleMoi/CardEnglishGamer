package com.kteam.cardenglishgamer.model;

/**
 * Created by Mo on 2017/7/7.
 * 示例：题型的基础类。
 */

public class BaseQuestion {
    public String getB_title() {
        return b_title;
    }

    public void setB_title(String b_title) {
        this.b_title = b_title;
    }

    public String getB_answer() {
        return b_answer;
    }

    public void setB_answer(String b_answer) {
        this.b_answer = b_answer;
    }

    private String b_title;
    private String b_answer;
}
