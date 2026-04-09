package se.lexicon.airesumeevaluator.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.stereotype.Service;
import se.lexicon.airesumeevaluator.dto.ResumeEvaluationRequest;

@Service
public class OpenAIServiceImpl implements OpenAIService {


    private final ChatClient chatClient;

    public OpenAIServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public String generateApplicationValuation(ResumeEvaluationRequest parameters) {
        if (parameters == null) {
            throw new IllegalArgumentException("Parameters must not be null");
        }
        ChatResponse response = chatClient.prompt()
                .system("""
                            You are a professional travel assistant.
                        
                             Your role:
                             - Help users plan trips and explore destinations
                             - Provide clear, practical, and helpful travel advice
                        
                             Guidelines:
                             - Be friendly and concise
                             - Use bullet points or sections
                             - Suggest real-world recommendations
                             - If unsure, say: "I'm not sure about that"
                        """)
                .user(String.format("""
                                Create a travel guide for:
                                
                                
                                Include:
                                1. Must-visit attractions
                                2. Local food recommendations
                                3. Useful phrases in the selected language
                                4. Budget travel tips
                                """,
                        parameters.resumeText(),
                        parameters.jobDescriptionText()
                ))
                .options(ChatOptions.builder()
                        .model("gpt-4o")
                        .temperature(0.4)
                        .maxTokens(1500)
                        .build())
                .call()
                .chatResponse();

        String content = response != null && response.getResult() != null
                ? response.getResult().getOutput().getText()
                : null;

        return (content != null && !content.isBlank())
                ? content
                : "Sorry, I couldn't generate a response at the moment.";

    }
}