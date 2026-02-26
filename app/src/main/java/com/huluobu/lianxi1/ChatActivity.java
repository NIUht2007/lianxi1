package com.huluobu.lianxi1;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.huluobu.lianxi1.data.model.MessageModel;
import com.huluobu.lianxi1.viewmodel.ChatViewModel;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    
    // 请在这里填写你的 DeepSeek API 密钥
    private static final String DEEPSEEK_API_KEY = "sk-2ee4b65a421447ffbdfff0ba5a1cf7b1"; // 用户需要在这里填入自己的API密钥
    
    private ChatViewModel chatViewModel;
    private ListView messageListView;
    private EditText messageEditText;
    private Button sendButton;
    private ProgressBar loadingIndicator;
    private MessageAdapter messageAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat);
        
        // 初始化ViewModel
        chatViewModel = new ChatViewModel(DEEPSEEK_API_KEY);
        
        // 初始化UI组件
        messageListView = findViewById(R.id.message_list);
        messageEditText = findViewById(R.id.message_input);
        sendButton = findViewById(R.id.send_button);
        loadingIndicator = findViewById(R.id.loading_indicator);
        
        // 打印初始消息数量
        System.out.println("初始消息数量: " + chatViewModel.getMessages().size());
        for (int i = 0; i < chatViewModel.getMessages().size(); i++) {
            System.out.println("初始消息 " + i + ": " + chatViewModel.getMessages().get(i).getContent());
        }
        
        // 初始化适配器
        messageAdapter = new MessageAdapter(this, chatViewModel.getMessages());
        messageListView.setAdapter(messageAdapter);
        
        // 发送按钮点击事件
        sendButton.setOnClickListener(v -> sendMessage());
        
        // 回车发送消息
        messageEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                sendMessage();
                return true;
            }
            return false;
        });
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            androidx.core.graphics.Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        // 手动调用一次更新，确保初始消息显示
        messageAdapter.notifyDataSetChanged();
    }
    
    /**
     * 发送消息
     */
    private void sendMessage() {
        String message = messageEditText.getText().toString().trim();
        if (!message.isEmpty()) {
            // 显示加载动画
            loadingIndicator.setVisibility(View.VISIBLE);
            sendButton.setVisibility(View.GONE);
            sendButton.setEnabled(false);
            
            // 在后台线程中执行网络请求
            new Thread(() -> {
                // 发送消息并获取回复
                final List<MessageModel> updatedMessages = chatViewModel.sendMessage(message);
                
                // 打印消息数量和内容
                System.out.println("更新后的消息数量: " + updatedMessages.size());
                for (int i = 0; i < updatedMessages.size(); i++) {
                    System.out.println("消息 " + i + ": " + updatedMessages.get(i).getContent());
                }
                
                // 在主线程中更新UI
                runOnUiThread(() -> {
                    // 打印适配器状态
                    System.out.println("适配器消息数量: " + messageAdapter.getCount());
                    
                    // 更新适配器
                    messageAdapter.updateMessages(updatedMessages);
                    
                    // 打印更新后的适配器状态
                    System.out.println("更新后适配器消息数量: " + messageAdapter.getCount());
                    
                    // 滚动到最新消息
                    messageListView.smoothScrollToPosition(messageAdapter.getCount() - 1);
                    
                    // 隐藏加载动画，恢复按钮状态
                    loadingIndicator.setVisibility(View.GONE);
                    sendButton.setVisibility(View.VISIBLE);
                    sendButton.setEnabled(true);
                    messageEditText.setText("");
                });
            }).start();
        }
    }
}
