package com.huluobu.lianxi1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WelcomeActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val startButton: Button = findViewById(R.id.start_button)
        startButton.setOnClickListener {
            showUserAgreementDialog()
        }
    }
    
    /**
     * 显示用户协议对话框
     */
    private fun showUserAgreementDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("用户协议")
        builder.setMessage("欢迎使用聊天小工具！\n\n在使用本应用前，请您仔细阅读并同意以下用户协议：\n\n1. 本应用仅用于学习和测试目的\n2. 请勿发送违法或不良信息\n3. 我们尊重您的隐私，不会收集您的个人信息\n\n点击同意即表示您已阅读并接受上述协议。")
        builder.setPositiveButton("同意") { dialog, which ->
            // 同意协议，进入聊天界面
            Toast.makeText(this, "进入聊天工具", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ChatActivity::class.java))
        }
        builder.setNegativeButton("不同意") { dialog, which ->
            // 不同意协议，退出应用
            finish()
        }
        builder.setCancelable(false) // 禁止点击外部关闭
        builder.show()
    }
}
