package com.huluobu.lianxi1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.huluobu.lianxi1.viewmodel.GameViewModel

class MainActivity2 : AppCompatActivity() {
    
    private lateinit var gameViewModel: GameViewModel
    private lateinit var gameStatusTextView: TextView
    private lateinit var scoreTextView: TextView
    private lateinit var addScoreButton: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activitgit add .y_main2)
        
        // 初始化ViewModel
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        
        // 初始化UI组件
        gameStatusTextView = findViewById(R.id.game_status)
        scoreTextView = findViewById(R.id.score)
        addScoreButton = findViewById(R.id.add_score)
        
        // 观察游戏状态
        gameViewModel.gameState.observe(this) { game ->
            gameStatusTextView.text = game.gameDescription
            scoreTextView.text = "分数: ${game.score}"
        }
        
        // 添加分数按钮
        addScoreButton.setOnClickListener {
            gameViewModel.updateScore(10) // 每次点击增加10分
        }
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
