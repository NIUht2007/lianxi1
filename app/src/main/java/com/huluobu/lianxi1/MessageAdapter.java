package com.huluobu.lianxi1;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.huluobu.lianxi1.data.model.MessageModel;
import java.util.ArrayList;
import java.util.List;

/**
 * 消息适配器
 */
public class MessageAdapter extends ArrayAdapter<MessageModel> {
    
    private List<MessageModel> messageList;
    
    public MessageAdapter(Context context, List<MessageModel> messages) {
        super(context, 0, new ArrayList<>(messages));
        this.messageList = new ArrayList<>(messages);
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 打印日志，查看是否被调用
        System.out.println("getView 被调用，位置: " + position);
        
        MessageModel message = messageList.get(position);
        
        // 打印消息内容
        System.out.println("消息内容: " + message.getContent());
        
        View view;
        if (message.isSent()) {
            // 自己发送的消息，显示在右侧
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_message_sent, parent, false);
        } else {
            // 接收的消息，显示在左侧
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_message_received, parent, false);
        }
        
        // 设置消息内容
        TextView messageText = view.findViewById(R.id.message_text);
        messageText.setText(message.getContent());
        
        // 设置发送者
        if (!message.isSent()) {
            TextView senderText = view.findViewById(R.id.sender_text);
            senderText.setText(message.getSender());
        }
        
        // 设置时间
        TextView timeText = view.findViewById(R.id.time_text);
        CharSequence formattedTime = DateFormat.format("HH:mm", message.getTimestamp());
        timeText.setText(formattedTime);
        
        return view;
    }
    
    @Override
    public int getCount() {
        return messageList.size();
    }
    
    /**
     * 更新消息列表
     */
    public void updateMessages(List<MessageModel> newMessages) {
        // 打印日志，查看新消息数量
        System.out.println("更新消息列表，新消息数量: " + newMessages.size());
        
        // 清空并添加新消息
        messageList.clear();
        messageList.addAll(newMessages);
        
        // 打印更新后的消息数量
        System.out.println("更新后消息列表数量: " + messageList.size());
        
        // 通知适配器更新
        notifyDataSetChanged();
    }
}
