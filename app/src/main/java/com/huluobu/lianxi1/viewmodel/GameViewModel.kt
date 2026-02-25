package com.huluobu.lianxi1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.huluobu.lianxi1.data.model.GameModel
import com.huluobu.lianxi1.data.repository.GameRepository

/**
 * 游戏视图模型
 */
class GameViewModel : ViewModel() {
    
    private val repository = GameRepository()
    private val _gameState = MutableLiveData<GameModel>()
    val gameState: LiveData<GameModel> = _gameState
    
    init {
        loadGameInfo()
    }
    
    /**
     * 加载游戏信息
     */
    fun loadGameInfo() {
        _gameState.value = repository.getGameInfo()
    }
    
    /**
     * 开始游戏
     */
    fun startGame() {
        _gameState.value = repository.startGame()
    }
    
    /**
     * 更新游戏分数
     */
    fun updateScore(points: Int) {
        val currentGame = _gameState.value ?: return
        _gameState.value = repository.updateScore(currentGame.score, points)
    }
}
