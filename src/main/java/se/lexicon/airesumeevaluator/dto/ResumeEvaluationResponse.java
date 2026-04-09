package se.lexicon.airesumeevaluator.dto;

import java.util.List;

public record ResumeEvaluationResponse(
        int matchScore,
        String overallAssessment,
        List<String> strengths,
        List<String> missingSkills,
        List<String> recommendations,
        List<String> matchedKeywords,
        List<String> missingKeywords
) {
}
