package com.kteam.cardenglishgamer;


import com.kteam.cardenglishgamer.util.netty.client.connector.DefaultCommonClientConnector;
import com.kteam.cardenglishgamer.util.netty.common.Message;

import org.junit.Test;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

import static com.kteam.cardenglishgamer.util.netty.common.NettyCommonProtocol.REQUEST;

/**
 * Created by Mo on 2017/7/24.
 */

public class SocketTest {
    @Test
    public void connection_test() throws Exception {


        DefaultCommonClientConnector connector = new DefaultCommonClientConnector();
        Channel channel = connector.connect(8082,"119.23.29.129");
        Message message = new Message();
        message.sign(REQUEST);
        message.data("HelloWorld!");

        channel.writeAndFlush(message).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if(!future.isSuccess()){
                    System.out.println("send failed!");
                }
            }
        });

    }
}

