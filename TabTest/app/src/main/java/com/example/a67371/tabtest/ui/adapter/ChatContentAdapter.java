package com.example.a67371.tabtest.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a67371.tabtest.R;
import com.example.a67371.tabtest.ui.bean.ChatContent;

import java.util.List;


/**
 * Created by wenjie on 2018/3/9.
 */

public class ChatContentAdapter extends ArrayAdapter {
    private int resourceId;

    public ChatContentAdapter(Context context, int textViewResourceId,
                              List<ChatContent> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatContent chatContent = (ChatContent)getItem(position); // 获取当前项的 ChatContent 实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent,
                false);
        Boolean isI = chatContent.getIsI();
        if(isI) {
            ImageView imageRight = (ImageView) view.findViewById(R.id.chat_image_right);
            TextView timeRight = (TextView) view.findViewById(R.id.chat_time_right);
            TextView contentRight = (TextView) view.findViewById(R.id.chat_content_right);
            ImageView imageLeft = (ImageView) view.findViewById(R.id.chat_image_left);
            RelativeLayout layoutLeft = (RelativeLayout) view.findViewById(R.id.chat_left);
            imageRight.setImageResource(chatContent.getImageId());
            timeRight.setText(chatContent.getTime());
            contentRight.setText(chatContent.getContent());
            imageLeft.setVisibility(View.GONE);
            layoutLeft.setVisibility(View.GONE);
            return view;
        }
        else {
            ImageView imageLeft = (ImageView) view.findViewById(R.id.chat_image_left);
            TextView timeLeft = (TextView) view.findViewById(R.id.chat_time_left);
            TextView contentLeft = (TextView) view.findViewById(R.id.chat_content_left);
            ImageView imageRight = (ImageView) view.findViewById(R.id.chat_image_right);
            RelativeLayout layoutRight = (RelativeLayout) view.findViewById(R.id.chat_right);
            imageLeft.setImageResource(chatContent.getImageId());
            timeLeft.setText(chatContent.getTime());
            contentLeft.setText(chatContent.getContent());
            imageRight.setVisibility(View.GONE);
            layoutRight.setVisibility(View.GONE);
            return view;
        }

    }
}
