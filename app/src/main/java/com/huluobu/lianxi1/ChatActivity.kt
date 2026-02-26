package com.huluobu.lianxi1

import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.huluobu.lianxi1.viewmodel.ChatViewModel

class ChatActivity : AppCompatActivity() {
    
    private lateinit var chatViewModel: ChatViewModel
    private lateinit var messageListView: ListView
    private lateinit var messageEditText: EditText
    private lateinit var sendButton: Button
    private lateinit var messageAdapter: MessageAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat)
        
        // 初始化ViewModel
        chatViewModel = ViewModelProvider(this).get(ChatViewModel::class.java)
        
        // 初始化UI组件
        messageListView = findViewById(R.id.message_list)
        messageEditText = findViewById(R.id.message_input)
        sendButton = findViewById(R.id.send_button)
        
        // 初始化适配器
        messageAdapter = MessageAdapter(this, emptyList())
        messageListView.adapter = messageAdapter
        
        // 观察消息列表
        chatViewModel.messages.observe(this) { messages ->
            messageAdapter.updateMessages(messages)
            // 滚动到底部
            messageListView.smoothScrollToPosition(messageAdapter.count - 1)
        }
        
        // 发送按钮点击事件
        sendButton.setOnClickListener {
            sendMessage()
        }
        
        // 回车发送消息
        messageEditText.setOnEditorActionListener(TextView.OnEditorActionListener {
            v, actionId, event ->
            if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                sendMessage()
                return@OnEditorActionListener true
            }
            false
        })
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    
    /**
     * 发送消息
     */
    private fun sendMessage() {
        val message = messageEditText.text.toString().trim()
        if (message.isNotBlank()) {
            chatViewModel.sendMessage(message)
            messageEditText.text.clear()
        }
    }
}
