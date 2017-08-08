package com.kteam.cardenglishgamer.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Mo on 2017/8/4.
 */

public class ClientConnectorStartup {
    private static final Logger logger = LoggerFactory.getLogger(ClientConnectorStartup.class);
    public static void go() {

//        DefaultCommonClientConnector clientConnector = new DefaultCommonClientConnector();
//        Channel channel = clientConnector.connect(8082, "10.0.2.2");
//        User user = new User(1, "dubbo");
//        Message message = new Message();
//        message.sign(REQUEST);
        //获取到channel发送双方规定的message格式的信息
//        channel.writeAndFlush(message).addListener(new ChannelFutureListener() {
//
//            public void operationComplete(ChannelFuture future) throws Exception {
//                if(!future.isSuccess()) {
//                    logger.info("send fail,reason is {}",future.cause().getMessage());
//                }
//            }
//        });

//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        while(true){
//            try {
//                channel.writeAndFlush(in.readLine() + "\r\n");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        //防止对象处理发生异常的情况
//        DefaultCommonClientConnector.MessageNonAck msgNonAck = new DefaultCommonClientConnector.MessageNonAck(message, channel);
//        clientConnector.addNeedAckMessageInfo(msgNonAck);
    }

    public static class User {

        private Integer id;

        private String username;


        public User(Integer id, String username) {
            this.id = id;
            this.username = username;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public String toString() {
            return "User [id=" + id + ", username=" + username + "]";
        }

    }
}
