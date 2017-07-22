package com.kteam.cardenglishgamer.util;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/**
 * Created by Mo on 2017/7/16.
 * Socket工具封装类
 */

public class SocketUtil {
    private Socket mSocket = null;
    private String mHost;
    private int mPort;
    private TrustManager[] tm;
    private OutputStream os;
    private InputStream is;
    Emitter mEmitter;
    Receiver mReceiver;

    /**
     * SocketUtil构造器
     * 一般与Connect连用
     * 只进行了参数初始化，不耗时
     * @param host 目标主机地址
     * @param port 目标端口号
     * @param tm 证书管理实例
     */
    public SocketUtil(String host,int port,TrustManager[] tm){
        this.mHost = host;
        this.mPort = port;
        this.tm = tm;
    }

    /**
     * SocketUtil缺省构造器
     * 一般与Connect连用
     * 只进行了参数初始化，不耗时
     * @param host 目标主机地址
     * @param port 目标端口号
     */
    public SocketUtil(String host,int port){
        this(host,port,new TrustManager[]{new MyX509TrustManager()});
    }

    /**
     * SSLSocket初始化
     * @return SSLSocket实例
     */
    private SSLSocket initSSLSocket(){
        SSLContext sc ;
        try {
            //获取遵循“TLS”协议的SSLContext
            sc = SSLContext.getInstance("TLS");
            //SSLContext初始化，参数三：密钥管理，证书管理，安全码
            sc.init(null,tm,new SecureRandom());
            //获取Socket工厂
            SSLSocketFactory ssf = sc.getSocketFactory();
            //利用Socket工厂获取Socket实例
            SSLSocket ss = (SSLSocket) ssf.createSocket(mHost,mPort);
            return ss;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return  null;
    }

    /**
     * Sockrt初始化
     * @return Socket实例
     */
    private Socket initSocket(){
        try {
            //返回Socket实例
            return new Socket(mHost,mPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取Socket实例
     * @return Socket实例
     */
    public Socket getmSocket(){
        return mSocket;
    }

    public Emitter getmEmitter() {
        return mEmitter;
    }


    public Receiver getmReceiver() {
        return mReceiver;
    }


    /**
     * Socket建立连接
     * 一般需要开启线程，耗时较长。
     * @throws IOException
     */
    public Emitter connect() throws IOException {
        //判断是否已经调用过connect函数
        if (mEmitter == null && mReceiver == null) {
            //判决是否为https协议
            if(isHttps(mHost))
                mSocket = initSSLSocket();
            else mSocket = initSocket();
            os = mSocket.getOutputStream();
            is = mSocket.getInputStream();
            mEmitter = new Emitter();
            mReceiver = new Receiver();
        }
        return mEmitter;

    }

    /**
     * Socket断开连接
     * 一般需要开启线程，耗时较长。
     * @throws IOException 关闭流抛出的异常
     */
    public void disconnect() throws IOException {
        mSocket.shutdownOutput();
        mSocket.shutdownInput();
        mSocket.close();
    }

    /**
     * 判决是否为https协议
     * @param url 源地址
     * @return 判决结果
     */
    private static boolean isHttps(String url) {
        return url.startsWith("https");
    }

    /**
     * 内部类
     * 主要用于发送信息
     */
    public class Emitter{
        PrintStream ps ;
        DataOutputStream dos;
        ObjectOutputStream oos;
        public void sendValueOf(String str){
            if(ps!=null)
                ps.close();
            ps = new PrintStream(os);
            ps.print(str);
            ps.flush();
        }
        public void addValueOf(String str){
            if(ps == null)
                ps = new PrintStream(os);
            ps.print(str);
        }
        public void sendString(){
            ps.flush();
            ps.close();
        }
        public void sendValueOf(File file) throws IOException {
            int length = 0;
            if(dos!=null)
                dos.close();
            dos = new DataOutputStream(os);
            FileInputStream fin = new FileInputStream(file);
            byte[] sendByte = new byte[1024];
            dos.writeUTF(file.getName());
            while((length = fin.read(sendByte,0,sendByte.length))>0){
                dos.write(sendByte,0,length);
                dos.flush();
            }
        }
        public void sendValueOf(Object object) throws IOException {
            if(oos == null)
                oos = new ObjectOutputStream(os);
            oos.writeObject(object);
            oos.flush();
        }
    }

    /**
     * 内部类
     * 主要用于接受信息
     */
    class Receiver{

        public String getString() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = br.readLine())!=null){
                sb.append(line);
            }
            return sb.toString();
        }

        public Object getObject() throws IOException, ClassNotFoundException {
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(is));
            return ois.readObject();
        }

        public void getFile(String path) throws IOException {
            DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
            File file = new File(path);
            FileOutputStream fos = new FileOutputStream(file);
            byte[] inputByte = new byte[1024];
            int length = 0;
            while((length = dis.read(inputByte,0,inputByte.length))>0){
                fos.write(inputByte);
                fos.flush();
            }
        }
    }
}
