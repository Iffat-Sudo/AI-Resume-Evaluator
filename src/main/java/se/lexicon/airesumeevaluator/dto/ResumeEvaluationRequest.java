package se.lexicon.airesumeevaluator.dto;

import jakarta.validation.constraints.NotBlank;

public record ResumeEvaluationRequest(
       @NotBlank String jobDescriptionText,

       @NotBlank String resumeText) {
}
