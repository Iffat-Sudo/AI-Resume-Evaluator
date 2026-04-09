package se.lexicon.airesumeevaluator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class OpenAIServiceImpl extends OpenAIService {

    private final ChatClient chatClient;
    public OpenAIServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public String processingSimpleChatQuery(String question){


    }


}
