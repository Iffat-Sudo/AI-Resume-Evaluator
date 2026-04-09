package se.lexicon.airesumeevaluator.service;

import se.lexicon.airesumeevaluator.dto.ResumeEvaluationRequest;
import se.lexicon.airesumeevaluator.dto.ResumeEvaluationResponse;

public interface OpenAIService {

    ResumeEvaluationResponse generateApplicationValuation(ResumeEvaluationRequest parameters);
}
