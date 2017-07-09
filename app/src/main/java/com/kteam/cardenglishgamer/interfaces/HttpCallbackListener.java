package com.kteam.cardenglishgamer.interfaces;

/**
 * Created by Mo on 2017/7/7.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
