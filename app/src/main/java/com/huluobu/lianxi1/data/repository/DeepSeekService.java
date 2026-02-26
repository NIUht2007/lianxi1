package com.huluobu.lianxi1.data.repository;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DeepSeekService {
    private static final String TAG = "DeepSeekService";
    private static final String API_URL = "https://api.deepseek.com/v1/chat/completions";
    private String apiKey;
    private OkHttpClient client;

    public DeepSeekService(String apiKey) {
        this.apiKey = apiKey;
        this.client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    public String getResponse(String userMessage) {
        // 检查 API 密钥
        if (apiKey == null || apiKey.isEmpty()) {
            return "请先设置 DeepSeek API 密钥";
        }

        // 构建请求体
        JsonObject requestObject = new JsonObject();
        requestObject.addProperty("model", "deepseek-chat");
        
        JsonArray messagesArray = new JsonArray();
        JsonObject messageObject = new JsonObject();
        messageObject.addProperty("role", "user");
        messageObject.addProperty("content", userMessage);
        messagesArray.add(messageObject);
        
        requestObject.add("messages", messagesArray);
        
        String requestBody = requestObject.toString();

        // 创建请求
        Request request = new Request.Builder()
                .url(API_URL)
                .post(RequestBody.create(requestBody, MediaType.parse("application/json")))
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            // 发送请求
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                // 解析 JSON 响应
                return parseResponse(responseBody);
            } else {
                Log.e(TAG, "API 请求失败: " + response.code() + " " + response.message());
                return "AI 服务暂时不可用，请稍后再试";
            }
        } catch (Exception e) {
            Log.e(TAG, "网络请求异常", e);
            return "网络连接失败，请检查网络设置";
        }
    }

    /**
     * 解析 API 响应
     */
    private String parseResponse(String responseBody) {
        try {
            JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
            // 检查是否有 choices 字段
            if (jsonObject.has("choices")) {
                // 获取第一个 choice
                JsonObject firstChoice = jsonObject.getAsJsonArray("choices").get(0).getAsJsonObject();
                // 获取 message 字段
                JsonObject message = firstChoice.getAsJsonObject("message");
                // 获取 content 字段
                if (message.has("content")) {
                    return message.get("content").getAsString();
                }
            }
            return "AI 回复格式错误";
        } catch (Exception e) {
            Log.e(TAG, "解析响应失败", e);
            return "AI 回复格式错误";
        }
    }
}
