package com.huluobu.lianxi1.data.repository;

import com.huluobu.lianxi1.data.model.MessageModel;
import java.util.ArrayList;
import java.util.List;

/**
 * 聊天数据仓库
 */
public class ChatRepository {
    
    private int messageId = 0;
    private DeepSeekService deepSeekService;
    
    /**
     * 构造函数
     */
    public ChatRepository(String apiKey) {
        this.deepSeekService = new DeepSeekService(apiKey);
    }
    
    /**
     * 获取初始消息列表
     */
    public List<MessageModel> getInitialMessages() {
        List<MessageModel> messages = new ArrayList<>();
        messages.add(new MessageModel(
            messageId++,
            "你好！欢迎使用聊天小工具",
            "系统",
            System.currentTimeMillis(),
            false
        ));
        messages.add(new MessageModel(
            messageId++,
            "我是基于DeepSeek AI模型的聊天助手，有什么可以帮你的吗？",
            "AI",
            System.currentTimeMillis(),
            false
        ));
        return messages;
    }
    
    /**
     * 发送消息
     */
    public MessageModel sendMessage(String content) {
        return new MessageModel(
            messageId++,
            content,
            "我",
            System.currentTimeMillis(),
            true
        );
    }
    
    /**
     * 获取AI回复
     */
    public MessageModel getAIReply(String userMessage) {
        String aiResponse;
        try {
            aiResponse = deepSeekService.getResponse(userMessage);
        } catch (Exception e) {
            aiResponse = "抱歉，AI服务暂时不可用，请稍后再试。\n错误信息：" + e.getMessage();
        }
        
        return new MessageModel(
            messageId++,
            aiResponse,
            "AI",
            System.currentTimeMillis(),
            false
        );
    }
}
