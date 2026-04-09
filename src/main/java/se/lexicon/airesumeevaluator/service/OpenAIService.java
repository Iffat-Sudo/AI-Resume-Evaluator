package se.lexicon.airesumeevaluator.service;

import se.lexicon.airesumeevaluator.dto.ResumeEvaluationRequest;

public interface OpenAIService {

    String generateApplicationValuation(ResumeEvaluationRequest parameters);
}
