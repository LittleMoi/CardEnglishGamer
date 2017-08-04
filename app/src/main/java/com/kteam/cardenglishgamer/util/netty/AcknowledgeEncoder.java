package com.kteam.cardenglishgamer.util.netty;

import com.kteam.cardenglishgamer.util.netty.common.Acknowledge;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import static com.kteam.cardenglishgamer.util.netty.common.NettyCommonProtocol.ACK;
import static com.kteam.cardenglishgamer.util.netty.common.NettyCommonProtocol.MAGIC;
import static com.kteam.cardenglishgamer.util.netty.serializer.SerializerHolder.serializerImpl;




/**
 * 
 * @author BazingaLyn
 * @description ack的编码器
 * @time
 * @modifytime
 */
@ChannelHandler.Sharable
public class AcknowledgeEncoder extends MessageToByteEncoder<Acknowledge> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Acknowledge ack, ByteBuf out) throws Exception {
        byte[] bytes = serializerImpl().writeObject(ack);
        out.writeShort(MAGIC)
                .writeByte(ACK)
                .writeByte(0)
                .writeLong(ack.sequence())
                .writeInt(bytes.length)
                .writeBytes(bytes);
    }
}
