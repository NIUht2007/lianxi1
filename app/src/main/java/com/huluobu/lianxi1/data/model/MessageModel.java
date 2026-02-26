package com.huluobu.lianxi1.data.model;

/**
 * 聊天消息模型
 */
public class MessageModel {
    private int id;
    private String content;
    private String sender;
    private long timestamp;
    private boolean isSent; // 是否是自己发送的消息
    
    public MessageModel(int id, String content, String sender, long timestamp, boolean isSent) {
        this.id = id;
        this.content = content;
        this.sender = sender;
        this.timestamp = timestamp;
        this.isSent = isSent;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getSender() {
        return sender;
    }
    
    public void setSender(String sender) {
        this.sender = sender;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    public boolean isSent() {
        return isSent;
    }
    
    public void setSent(boolean sent) {
        isSent = sent;
    }
}
