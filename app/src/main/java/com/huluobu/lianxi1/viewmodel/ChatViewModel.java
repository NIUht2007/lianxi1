package com.huluobu.lianxi1.viewmodel;

import com.huluobu.lianxi1.data.model.MessageModel;
import com.huluobu.lianxi1.data.repository.ChatRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * 聊天视图模型
 */
public class ChatViewModel {
    
    private ChatRepository repository;
    private List<MessageModel> messages = new ArrayList<>();
    private boolean isLoading = false;
    
    /**
     * 构造函数
     */
    public ChatViewModel(String apiKey) {
        this.repository = new ChatRepository(apiKey);
        loadInitialMessages();
    }
    
    /**
     * 加载初始消息
     */
    private void loadInitialMessages() {
        messages = new ArrayList<>(repository.getInitialMessages());
    }
    
    /**
     * 获取消息列表
     */
    public List<MessageModel> getMessages() {
        return messages;
    }
    
    /**
     * 获取加载状态
     */
    public boolean isLoading() {
        return isLoading;
    }
    
    /**
     * 发送消息
     */
    public List<MessageModel> sendMessage(String content) {
        if (content == null || content.trim().isEmpty()) {
            return messages;
        }
        
        // 添加发送的消息
        MessageModel sentMessage = repository.sendMessage(content);
        messages.add(sentMessage);
        
        // 显示加载状态
        isLoading = true;
        
        // 获取AI回复
        MessageModel replyMessage = repository.getAIReply(content);
        messages.add(replyMessage);
        
        // 关闭加载状态
        isLoading = false;
        
        return messages;
    }
}
