package com.huluobu.lianxi1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.huluobu.lianxi1.data.model.MessageModel
import com.huluobu.lianxi1.data.repository.ChatRepository

/**
 * 聊天视图模型
 */
class ChatViewModel : ViewModel() {
    
    private val repository = ChatRepository()
    private val _messages = MutableLiveData<List<MessageModel>>()
    val messages: LiveData<List<MessageModel>> = _messages
    
    init {
        loadInitialMessages()
    }
    
    /**
     * 加载初始消息
     */
    private fun loadInitialMessages() {
        _messages.value = repository.getInitialMessages()
    }
    
    /**
     * 发送消息
     */
    fun sendMessage(content: String) {
        if (content.isBlank()) return
        
        val currentMessages = _messages.value.orEmpty().toMutableList()
        
        // 添加发送的消息
        val sentMessage = repository.sendMessage(content)
        currentMessages.add(sentMessage)
        _messages.value = currentMessages
        
        // 模拟接收回复
        val replyMessage = repository.getReply()
        currentMessages.add(replyMessage)
        _messages.value = currentMessages
    }
}
