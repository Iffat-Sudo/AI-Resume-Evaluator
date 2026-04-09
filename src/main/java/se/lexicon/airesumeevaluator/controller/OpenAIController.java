package se.lexicon.airesumeevaluator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping

public class OpenAIController {
    @GetMapping
    public String processSimpleChatQuery(@RequestParam String question) {
        System.out.println("Question: " + question);
        return "Answer:"+ question;

    }
}
