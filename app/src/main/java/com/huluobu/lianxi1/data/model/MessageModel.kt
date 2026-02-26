package com.huluobu.lianxi1.data.model

/**
 * 聊天消息模型
 */
data class MessageModel(
    val id: Int,
    val content: String,
    val sender: String,
    val timestamp: Long,
    val isSent: Boolean // 是否是自己发送的消息
)
