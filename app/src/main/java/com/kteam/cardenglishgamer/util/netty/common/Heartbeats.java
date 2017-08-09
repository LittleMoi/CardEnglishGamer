package com.kteam.cardenglishgamer.util.netty.common;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import static com.kteam.cardenglishgamer.util.netty.common.NettyCommonProtocol.HEAD_LENGTH;
import static com.kteam.cardenglishgamer.util.netty.common.NettyCommonProtocol.HEARTBEAT;
import static com.kteam.cardenglishgamer.util.netty.common.NettyCommonProtocol.MAGIC;

/**
 * 心跳包
 */
public class Heartbeats {

    private static final ByteBuf HEARTBEAT_BUF;

    //静态块，防止多次初始化浪费性能
    static {
        ByteBuf buf = Unpooled.buffer(HEAD_LENGTH);
        buf.writeShort(MAGIC);
        buf.writeByte(HEARTBEAT);
        buf.writeByte(0);
        buf.writeLong(0);
        buf.writeInt(0);
        HEARTBEAT_BUF = Unpooled.unmodifiableBuffer(Unpooled.unreleasableBuffer(buf));
    }

    public static ByteBuf heartbeatContent() {
        return HEARTBEAT_BUF.duplicate();
    }
}
