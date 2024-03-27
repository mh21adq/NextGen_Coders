package com.example.next.Model.OpenAI;

import java.util.List;

public class OpenAIResponse {
    public List<Choice> choices;

    public List<Choice> getChoices() {
        return choices;
    }


    public static class Choice {
        public Message message;

        public Message getMessage() {
            return message;
        }

    }

    public static class Message {
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
