package com.example.next;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;

import com.example.next.Model.OpenAI.OpenAIRequest;

public class OpenAIRequestTest {
    private OpenAIRequest openAIRequest;
    private List<OpenAIRequest.Message> messages;

    @Before
    public void setUp() {
        // Initialize your messages list with some sample messages
        messages = Arrays.asList(
                new OpenAIRequest.Message("user", "Hello, AI!"),
                new OpenAIRequest.Message("ai", "Hello, human!")
        );

        // Create an instance of OpenAIRequest with the sample model and messages
        openAIRequest = new OpenAIRequest("test-model", messages);
    }

    @Test
    public void testMessageContents() {
        // Verify that the content of the messages is as expected
        assertEquals("Hello, AI!", messages.get(0).getContent());
        assertEquals("Hello, human!", messages.get(1).getContent());
    }

    @Test
    public void testMessageRoles() {
        // Verify that the roles of the messages are as expected
        assertEquals("user", messages.get(0).getRole());
        assertEquals("ai", messages.get(1).getRole());
    }

    @Test
    public void testModelIsCorrect() {
        // Verify that the model name is correctly set
        assertEquals("test-model", openAIRequest.getModel());
    }
}
