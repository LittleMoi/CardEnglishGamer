package com.kteam.cardenglishgamer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kteam.cardenglishgamer.R;
import com.kteam.cardenglishgamer.util.netty.client.connector.DefaultCommonClientConnector;
import com.kteam.cardenglishgamer.util.netty.common.Message;
import com.kteam.cardenglishgamer.util.netty.common.NettyCommonProtocol;

import java.util.ArrayList;
import java.util.List;

import io.netty.channel.Channel;

public class MainActivity extends AppCompatActivity {

    private Channel channel;
    private TextView inputText;
    private Button sendButton;
    private RecyclerView msgRecyclerView;
    private List<Message> msgList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputText = (TextView) findViewById(R.id.inputText);
        sendButton = (Button) findViewById(R.id.sendButton);
        msgRecyclerView = (RecyclerView) findViewById(R.id.msgView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(manager);
        final MessageAdapter adapter = new MessageAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content;
                if(!(content = inputText.getText().toString()).isEmpty()){
                    Message msg = new Message();
                    msg.sign(NettyCommonProtocol.SERVICE_1);
                    msg.data(content);
                    channel.writeAndFlush(msg);
                }
            }
        });
        DefaultCommonClientConnector clientConnector = new DefaultCommonClientConnector() {
            @Override
            public void onReceive(Object msg) {
                if(msg instanceof Message){
                Message mmsg = (Message)msg;
                    if( mmsg.sign() == NettyCommonProtocol.SERVICE_1||mmsg.sign() == NettyCommonProtocol.SERVICE_2) {
                        msgList.add(mmsg);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                                msgRecyclerView.scrollToPosition(msgList.size()-1);
                                inputText.setText("");
                            }
                        });

                    }
                }
            }

            @Override
            public void onException(String exception) {

            }
        };
        channel = clientConnector.connect(8082, "10.0.2.2");
    }
}
