package se.lexicon.airesumeevaluator.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import se.lexicon.airesumeevaluator.dto.ResumeEvaluationRequest;
import se.lexicon.airesumeevaluator.dto.ResumeEvaluationResponse;
import se.lexicon.airesumeevaluator.service.OpenAIService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

public class OpenAIController {
    private final OpenAIService openAIService;

    @PostMapping("/resume")
    public ResumeEvaluationResponse processResume(@RequestBody @Valid ResumeEvaluationRequest request) {
        System.out.println("Processing job application " + request);
        return openAIService.generateApplicationValuation(request);

    }
}
