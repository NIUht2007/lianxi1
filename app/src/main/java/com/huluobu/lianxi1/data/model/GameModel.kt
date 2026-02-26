package com.huluobu.lianxi1.data.model

/**
 * 游戏数据模型
 */
data class GameModel(
    val gameId: Int,
    val gameName: String,
    val gameDescription: String,
    val isStarted: Boolean = false,
    val score: Int = 0
)
