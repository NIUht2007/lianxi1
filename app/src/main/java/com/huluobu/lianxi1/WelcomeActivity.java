package com.huluobu.lianxi1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WelcomeActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            androidx.core.graphics.Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(v -> showUserAgreementDialog());
    }
    
    /**
     * 显示用户协议对话框
     */
    private void showUserAgreementDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("AI 聊天助手 - 用户协议");
        builder.setMessage("欢迎使用 AI 聊天助手！\n\n在使用本应用前，请您仔细阅读并同意以下用户协议：\n\n1. 服务条款\n   - 本应用基于 DeepSeek AI 模型，仅用于学习和测试目的\n   - 请勿使用本应用发送违法或不良信息\n   - 请勿将本应用用于商业用途\n\n2. 隐私政策\n   - 我们尊重您的隐私，不会收集您的个人信息\n   - 您与 AI 的对话内容仅用于生成回复，不会被存储或分享\n   - 请不要在对话中包含敏感信息\n\n3. 使用规范\n   - 请遵守相关法律法规和公序良俗\n   - 请勿滥用 AI 服务，避免频繁发送无意义的请求\n   - 对于 AI 生成的内容，请自行判断其准确性和合法性\n\n点击同意即表示您已阅读并接受上述协议。");
        builder.setPositiveButton("同意", (dialog, which) -> {
            // 同意协议，进入聊天界面
            Toast.makeText(this, "进入 AI 聊天助手", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, ChatActivity.class));
        });
        builder.setNegativeButton("不同意", (dialog, which) -> {
            // 不同意协议，退出应用
            finish();
        });
        builder.setCancelable(false); // 禁止点击外部关闭
        builder.show();
    }
}
