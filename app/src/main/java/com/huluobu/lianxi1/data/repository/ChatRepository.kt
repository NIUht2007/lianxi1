package com.huluobu.lianxi1.data.repository

import com.huluobu.lianxi1.data.model.MessageModel

/**
 * 聊天数据仓库
 */
class ChatRepository {
    
    private var messageId = 0
    
    /**
     * 获取初始消息列表
     */
    fun getInitialMessages(): List<MessageModel> {
        return listOf(
            MessageModel(
                id = messageId++,
                content = "你好！欢迎使用聊天小工具",
                sender = "系统",
                timestamp = System.currentTimeMillis(),
                isSent = false
            ),
            MessageModel(
                id = messageId++,
                content = "你可以在这里发送消息",
                sender = "系统",
                timestamp = System.currentTimeMillis(),
                isSent = false
            )
        )
    }
    
    /**
     * 发送消息
     */
    fun sendMessage(content: String): MessageModel {
        return MessageModel(
            id = messageId++,
            content = content,
            sender = "我",
            timestamp = System.currentTimeMillis(),
            isSent = true
        )
    }
    
    /**
     * 模拟接收回复
     */
    fun getReply(): MessageModel {
        val replies = listOf(
            "好的，我明白了",
            "这个想法不错！",
            "你说的对",
            "哈哈，有意思",
            "继续保持！"
        )
        val randomReply = replies.random()
        
        return MessageModel(
            id = messageId++,
            content = randomReply,
            sender = "系统",
            timestamp = System.currentTimeMillis(),
            isSent = false
        )
    }
}
