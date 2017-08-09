package com.kteam.cardenglishgamer.util.netty.client.connector;

import com.kteam.cardenglishgamer.util.netty.common.Heartbeats;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;


/**
 * 心跳响应器，与 {@link  io.netty.handler.timeout.IdleStateHandler}结合使用。
 */
@ChannelHandler.Sharable
public class ConnectorIdleStateTrigger extends ChannelInboundHandlerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(ConnectorIdleStateTrigger.class);

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state();
            if (state == IdleState.WRITER_IDLE) {
            	logger.info("need send heartbeats");
                ctx.writeAndFlush(Heartbeats.heartbeatContent());
                logger.info("already send heartbeats");
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
