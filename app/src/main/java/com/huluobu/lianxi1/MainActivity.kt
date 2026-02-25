package com.huluobu.lianxi1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val startButton: Button = findViewById(R.id.start_button)
        startButton.setOnClickListener {
            Toast.makeText(this, "游戏即将开始112233！", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java))
            // 这里可以添加跳转到游戏页面的逻辑
        }

    }

    override fun onResume() {
        super.onResume()
        // 添加游戏开始时需要的逻辑
    }
}
