package com.kteam.cardenglishgamer.util.netty.common;

/**
 * Netty的C/S端的之间约定的协议
 * 只服务于HEADER解析
 * @author Mo
 *
 */
public class NettyCommonProtocol {
    /**
     * HEADER 协议头参数部分
     */
    //协议头起始位
    public static final short MAGIC = (short) 0xbabe;
    //协议头长度
    public static final int HEAD_LENGTH = 16;

    /**
     * BODY 消息标志位（用于解析）
     * 主要为类别消息头
     * 此次更新后，每个作为解析的类必须内置标志位静态数。
     * 此次更新后，HEADER标志位与Message解析绑定。
     * Message将被当做普通信息类。
     */
    //内置Message类
    public static final byte MESSAGE = 1;
    //自定义类
    public static final byte SERVICE = 2;
    public static final byte SERVICE_1 = 3;
    public static final byte SERVICE_2 = 4;
    public static final byte SERVICE_3 = 5;
    public static final byte SERVICE_4 = 6;

    //Acknowlege类
    public static final byte ACK = 126;
    //HeatBeats类
    public static final byte HEARTBEAT = 127;

    private byte sign;
    private byte status;
    private long id;
    private int bodyLength;

    public byte sign() {
        return sign;
    }

    public void sign(byte sign) {
        this.sign = sign;
    }

    public byte status() {
        return status;
    }

    public void status(byte status) {
        this.status = status;
    }

    public long id() {
        return id;
    }

    public void id(long id) {
        this.id = id;
    }

    public int bodyLength() {
        return bodyLength;
    }

    public void bodyLength(int bodyLength) {
        this.bodyLength = bodyLength;
    }

	@Override
	public String toString() {
		return "NettyCommonProtocol [sign=" + sign + ", status=" + status + ", id=" + id + ", bodyLength=" + bodyLength + "]";
	}

}
