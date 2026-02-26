package com.huluobu.lianxi1

import android.content.Context
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.huluobu.lianxi1.data.model.MessageModel

/**
 * 消息适配器
 */
class MessageAdapter(context: Context, messages: List<MessageModel>) :
    ArrayAdapter<MessageModel>(context, 0, messages) {
    
    private var messageList = messages.toMutableList()
    
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val message = messageList[position]
        
        val view = if (message.isSent) {
            // 自己发送的消息，显示在右侧
            LayoutInflater.from(context).inflate(R.layout.item_message_sent, parent, false)
        } else {
            // 接收的消息，显示在左侧
            LayoutInflater.from(context).inflate(R.layout.item_message_received, parent, false)
        }
        
        // 设置消息内容
        val messageText: TextView = view.findViewById(R.id.message_text)
        messageText.text = message.content
        
        // 设置发送者
        if (!message.isSent) {
            val senderText: TextView = view.findViewById(R.id.sender_text)
            senderText.text = message.sender
        }
        
        // 设置时间
        val timeText: TextView = view.findViewById(R.id.time_text)
        val formattedTime = DateFormat.format("HH:mm", message.timestamp)
        timeText.text = formattedTime.toString()
        
        return view
    }
    
    override fun getCount(): Int {
        return messageList.size
    }
    
    /**
     * 更新消息列表
     */
    fun updateMessages(newMessages: List<MessageModel>) {
        messageList.clear()
        messageList.addAll(newMessages)
        notifyDataSetChanged()
    }
}
