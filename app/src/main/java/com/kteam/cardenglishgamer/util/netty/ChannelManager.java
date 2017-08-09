package com.kteam.cardenglishgamer.util.netty;

import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;

/**
 * Created by Mo on 2017/8/9.
 */

public enum ChannelManager {
    INSTANCE;
    private ConcurrentHashMap<String,Channel> channels;
    ChannelManager(){

        channels = new ConcurrentHashMap<>();
    }

    /**
     *
     * @param channelIdAsLongText {@link io.netty.channel.ChannelId}的asLongText()
     * @return 根据唯一索引寻找到的Channel对象
     */
    public Channel get(String channelIdAsLongText){

        if(channelIdAsLongText.isEmpty()){
            return null;
        }
        return channels.get(channelIdAsLongText);
    }

    public Channel get(ChannelId id){
        if (id == null){
            return null;
        }
        return get(id.asLongText());
    }

    public void put(Channel channel){
        channels.put(channel.id().asLongText(),channel);
    }
}
