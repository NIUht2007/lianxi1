package com.huluobu.lianxi1.data.repository

import com.huluobu.lianxi1.data.model.GameModel

/**
 * 游戏数据仓库
 */
class GameRepository {
    
    /**
     * 获取游戏信息
     */
    fun getGameInfo(): GameModel {
        return GameModel(
            gameId = 1,
            gameName = "冒险小游戏",
            gameDescription = "一段有趣的冒险之旅",
            isStarted = false,
            score = 0
        )
    }
    
    /**
     * 开始游戏
     */
    fun startGame(): GameModel {
        return GameModel(
            gameId = 1,
            gameName = "冒险小游戏",
            gameDescription = "游戏已开始，祝你好运！",
            isStarted = true,
            score = 0
        )
    }
    
    /**
     * 更新游戏分数
     */
    fun updateScore(currentScore: Int, points: Int): GameModel {
        return GameModel(
            gameId = 1,
            gameName = "冒险小游戏",
            gameDescription = "游戏进行中",
            isStarted = true,
            score = currentScore + points
        )
    }
}
