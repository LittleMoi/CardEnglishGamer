package com.kteam.cardenglishgamer.activity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kteam.cardenglishgamer.R;
import com.kteam.cardenglishgamer.util.netty.common.Message;
import com.kteam.cardenglishgamer.util.netty.common.NettyCommonProtocol;

import java.util.List;

/**
 * Created by Mo on 2017/8/8.
 */

public class MessageAdapter extends Adapter<MessageAdapter.ViewHolder> {

    private List<Message> msgList;

    public MessageAdapter(List<Message> msgList) {
        this.msgList = msgList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Message msg = msgList.get(position);
        if(msg.sign() == NettyCommonProtocol.SERVICE_1){
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftText.setText(msg.data().toString());
        }else if(msg.sign() == NettyCommonProtocol.SERVICE_2){
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.rightText.setText(msg.data().toString());
        }
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView leftImage;
        ImageView rightImage;
        TextView leftText;
        TextView rightText;
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            leftImage = (ImageView) itemView.findViewById(R.id.message_left_image);
            rightImage = (ImageView) itemView.findViewById(R.id.message_right_image);
            leftText = (TextView) itemView.findViewById(R.id.message_left_text);
            rightText = (TextView) itemView.findViewById(R.id.message_right_text);
            leftLayout = (LinearLayout) itemView.findViewById(R.id.message_left_layout);
            rightLayout = (LinearLayout) itemView.findViewById(R.id.message_right_layout);

        }
    }

}
