package com.kteam.cardenglishgamer.util.netty.client.connector;

import io.netty.channel.Channel;

/**
 * Netty服务端常用接口
 * @author Mo
 */
public interface ClientConnector {
	
	Channel connect(int port,String host);
	
	void shutdownGracefully();
	
}
