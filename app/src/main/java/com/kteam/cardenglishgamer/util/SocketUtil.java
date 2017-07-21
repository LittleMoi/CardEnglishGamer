package com.kteam.cardenglishgamer.util;


import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    private OutputStream os;
    private InputStream is;
    Emitter mEmitter;
    Receiver mReceiver;
    public SocketUtil(String host,int port){
        try {
            mHost = host;
            mPort = port;
            URL url = new URL(host);
            if(isHttps(host))
                mSocket = initSSLSocket();
            else mSocket = initSocket();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private SSLSocket initSSLSocket(){
        SSLContext sc ;
        TrustManager[] tm = { new MyX509TrustManager() };
        try {
            sc = SSLContext.getInstance("TLS");
            sc.init(null,tm,new SecureRandom());
            SSLSocketFactory ssf = sc.getSocketFactory();
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

    private Socket initSocket(){
        try {
            return new Socket(mHost,mPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Socket getmSocket(){
        return mSocket;
    }


    public void connect() throws IOException {
        if (mEmitter == null && mReceiver == null) {
            os = mSocket.getOutputStream();
            is = mSocket.getInputStream();
            mEmitter = new Emitter();
            mReceiver = new Receiver();
        }

    }
    public void disconnect() throws IOException {
        mSocket.shutdownOutput();
        mSocket.shutdownInput();
        mSocket.close();
    }
    private static boolean isHttps(String url) {
        return url.startsWith("https");
    }

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

    class Receiver{

    }
}
