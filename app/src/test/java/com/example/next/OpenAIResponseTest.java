package com.example.next;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.next.Model.OpenAI.OpenAIResponse;

public class OpenAIResponseTest {
    private OpenAIResponse openAIResponse;
    private List<OpenAIResponse.Choice> choices;

    @Before
    public void setUp() {
        // Initialize the OpenAIResponse and its choices list
        openAIResponse = new OpenAIResponse();
        choices = new ArrayList<>();

        // Create a choice with a message
        OpenAIResponse.Choice choice = new OpenAIResponse.Choice();
        OpenAIResponse.Message message = new OpenAIResponse.Message();
        message.setContent("Test response content");

        // Set the message in the choice and add the choice to the choices list
        choice.message = message; // Direct access for brevity, consider using a constructor or setter in practice
        choices.add(choice);

        // Use reflection or modify OpenAIResponse to include a setter if needed to set the private choices list
        openAIResponse.choices = choices; // Direct access for brevity, consider using a setter in practice
    }

    @Test
    public void getChoices_ShouldReturnCorrectChoices() {
        // Assert that the choices list returned is the same as the one set
        assertEquals("The returned choices list should be the same as the one set.", choices, openAIResponse.getChoices());
    }

    @Test
    public void choiceMessageContent_ShouldBeCorrect() {
        // Retrieve the first choice and its message content
        String content = openAIResponse.getChoices().get(0).getMessage().getContent();

        // Assert that the message content is as expected
        assertEquals("Test response content", content);
    }

    @Test
    public void setContent_ShouldUpdateContentCorrectly() {
        // Set new content on the first message
        openAIResponse.getChoices().get(0).getMessage().setContent("Updated content");

        // Assert that the content was updated correctly
        assertEquals("Updated content", openAIResponse.getChoices().get(0).getMessage().getContent());
    }
}
