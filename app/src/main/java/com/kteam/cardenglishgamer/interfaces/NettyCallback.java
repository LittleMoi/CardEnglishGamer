package com.kteam.cardenglishgamer.interfaces;

/**
 * Created by Mo on 2017/8/8.
 */

public interface NettyCallback {
    public void onReceive(Object msg);
    public void onException(String exception);
}
