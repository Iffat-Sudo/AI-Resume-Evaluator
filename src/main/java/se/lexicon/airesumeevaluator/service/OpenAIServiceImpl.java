package se.lexicon.airesumeevaluator.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.stereotype.Service;
import se.lexicon.airesumeevaluator.dto.ResumeEvaluationRequest;
import se.lexicon.airesumeevaluator.dto.ResumeEvaluationResponse;

@Service
public class OpenAIServiceImpl implements OpenAIService {


    private final ChatClient chatClient;

    public OpenAIServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public ResumeEvaluationResponse generateApplicationValuation(ResumeEvaluationRequest parameters) {
        if (parameters == null) {
            throw new IllegalArgumentException("Parameters must not be null");
        }
        BeanOutputConverter<ResumeEvaluationResponse> converter = new BeanOutputConverter<>(ResumeEvaluationResponse.class);

        String format = converter.getFormat();
        return chatClient.prompt()
                .system("""
                            You are a professional resume evaluator.
                        
                             Your role:
                             - Analyze the resume and job description
                             - Evaluate how well the candidate matches the role
                             - Identify strengths and missing skills
                             - Provide actionable recommendations
                        
                             Guidelines:
                             - Be professional and concise
                             - Focus only on relevant information from the resume and job description
                             - Do NOT include explanations outside the structured output
                             - ALWAYS return valid JSON
                        
                              Return the result strictly in the following format:
                            """ + format)

                .user(String.format("""
                                Resume:
                                This can be a link instead, read the link content and use it as the resume link.
                                 %s
                                
                                Job Description:
                                  This can be a link instead, read the link content and use it as the job description link.
                                        %s
                                
                                        Evaluate the candidate against the job description and include:
                                                - overall match
                                                - strengths
                                                - missing skills
                                                - recommendations
                                                - matched keywords from the job description found in the resume
                                                - important keywords from the job description missing from the resume
                                """ ,
                                
                        parameters.resumeText(),
                        parameters.jobDescriptionText()
                ))
                .options(ChatOptions.builder()
                        .model("gpt-4o")
                        .temperature(0.4)
                        .maxTokens(1500)
                        .build())
                .call()
                .entity(ResumeEvaluationResponse.class);





    }
}