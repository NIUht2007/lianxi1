package com.huluobu.lianxi1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.huluobu.lianxi1.viewmodel.GameViewModel

class MainActivity : AppCompatActivity() {
    
    private lateinit var gameViewModel: GameViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // 初始化ViewModel
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val startButton: Button = findViewById(R.id.start_button)
        startButton.setOnClickListener {
            // 开始游戏
            gameViewModel.startGame()
            Toast.makeText(this, "游戏即将开始112233！", Toast.LENGTH_SHORT).show()
            // 跳转到MainActivity2
            startActivity(Intent(this, MainActivity2::class.java))
        }
    }
}
