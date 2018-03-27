package com.example.a67371.tabtest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a67371.tabtest.R;
import com.example.a67371.tabtest.UserData;
import com.example.a67371.tabtest.ui.adapter.ChatContentAdapter;
import com.example.a67371.tabtest.ui.bean.BackBean;
import com.example.a67371.tabtest.ui.bean.ChatContent;
import com.example.a67371.tabtest.ui.bean.ContentBean;
import com.example.a67371.tabtest.ui.dialog.EvaluateDialog;
import com.example.a67371.tabtest.ui.refresh.RefreshableView;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity {

    private String userName = UserData.getName();
    private String chaterName = "hy1";
    private List<ChatContent> chatList = new ArrayList<ChatContent>();
    private String jsonStr;

    Date time;
    Date today = new Date();
    int index = 0;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    ChatContentAdapter adapter;

    EvaluateDialog dialog;

    RefreshableView refreshableView;

    @BindView(R.id.chat_list)
    ListView listView;

    Handler handler=new Handler();

    Runnable addChatContent = new Runnable() {
        @Override
        public void run() {
            initAdapter(1);
        }
    };

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final float rating = dialog.getEvaluate().getRating();
            Http.criticDoctor(rating, userName, chaterName, new Http.OnCriticDoctor() {
                @Override
                public void OnCriticDoctor(String s) {
                    Gson gson = new Gson();
                    BackBean back = gson.fromJson(s, BackBean.class);
                    if(back.isSuccess()) Toast.makeText(ChatActivity.this,"评价成功！评分为"+rating+"分", Toast.LENGTH_SHORT).show();
                    else Toast.makeText(ChatActivity.this,"评价失败！", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.cancel();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        chaterName = intent.getStringExtra("chat_name");
        Toolbar toolbar = (Toolbar) findViewById(R.id.chat_bar);
        toolbar.setTitle(chaterName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        refreshableView = (RefreshableView) findViewById(R.id.refreshable_view);
        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                handler.post(addChatContent);
            }
        }, 0);
        initAdapter(0);
        Button send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText =(EditText) findViewById(R.id.input);
                String message = editText.getText().toString();
                if(message.equals("")) {
                    Toast.makeText(ChatActivity.this,"消息不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                editText.setText("");
                time=new Date();
                Http.sendMessage(userName, chaterName, message, new Http.OnSendMessage() {
                    @Override
                    public void OnSendMessage(String s) {
                        System.out.println(s);
                    }
                });
                ChatContent c = new ChatContent(message, R.drawable.head,df.format(time),true);
                chatList.add(c);
                ChatContentAdapter adapter = new ChatContentAdapter(ChatActivity.this, R.layout.chat_content, chatList);
                if(chatList.size()<7) listView.setStackFromBottom(false);
                else listView.setStackFromBottom(true);
                listView.setAdapter(adapter);
            }
        } );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_menu, menu);//加载menu布局
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.evaluate:
                dialog = new EvaluateDialog(ChatActivity.this, R.layout.evaluate_dialog, onClickListener);
                dialog.show();
                break;
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    void initAdapter(final int type) {
        if(index>=12) {
            Toast.makeText(ChatActivity.this,"没有消息记录", Toast.LENGTH_SHORT).show();
            refreshableView.finishRefreshing();
            return;
        }
        Http.getMessages(userName, chaterName, format.format(today), new Http.OnGetMessages() {
            @Override
            public void OnGetMessages(String s) {
                Gson gson = new Gson();
                ContentBean content = gson.fromJson(s, ContentBean.class);
                if((content.getMessages().get(0).getFrom().equals("error") && content.getMessages().get(0).getTo().equals("error"))||s.equals(jsonStr)) {
                    today = new Date(today.getTime() - 86400000L);
                    index++;
                    if(index<=10) initAdapter(type);
                    else {
                        today = new Date(today.getTime() - 86400000000L);
                        initAdapter(type);
                    }
                    if(index>=12) {
                        Toast.makeText(ChatActivity.this,"没有消息记录", Toast.LENGTH_SHORT).show();
                        refreshableView.finishRefreshing();
                        return;
                    }
                } else {
                    jsonStr = s;
                    List<ContentBean.MessagesBean> messagesBean = content.getMessages();
                    chatList = new ArrayList<ChatContent>();
                    for(int i = 0;i<messagesBean.size();i++) {
                        ChatContent chatContent = new ChatContent(messagesBean.get(i).getContent(), R.drawable.head, messagesBean.get(i).getTime(), messagesBean.get(i).getFrom().equals(userName)?true:false);
                        chatList.add(chatContent);
                    }
                    adapter = new ChatContentAdapter(ChatActivity.this, R.layout.chat_content, chatList);
                    if(chatList.size()<7) listView.setStackFromBottom(false);
                    else {
                        if(type==0) listView.setStackFromBottom(true);
                        else listView.setStackFromBottom(false);
                    }
                    listView.setAdapter(adapter);
                    refreshableView.finishRefreshing();
                }

            }
        });
    }
}
