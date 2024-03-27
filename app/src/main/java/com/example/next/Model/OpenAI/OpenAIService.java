package com.example.next.Model.OpenAI;

import com.example.next.Model.OpenAI.OpenAIRequest;
import com.example.next.Model.OpenAI.OpenAIResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface OpenAIService {
    @POST("v1/chat/completions")
    Call<OpenAIResponse> createChatCompletion(@Header("Authorization") String authorization, @Body OpenAIRequest request);
}
