package com.example.sentimentanalyzer.service;

import com.example.sentimentanalyzer.client.HuggingFaceClient;
import com.example.sentimentanalyzer.dto.RequestDto;
import com.example.sentimentanalyzer.dto.ResponseDto;
import org.springframework.stereotype.Service;


@Service
public class SentimentAnalyzerService {

    private final HuggingFaceClient huggingFaceClient;
    public SentimentAnalyzerService(HuggingFaceClient huggingFaceClient) {
        this.huggingFaceClient = huggingFaceClient;
    }

    public  ResponseDto analyze(RequestDto dto){

        ResponseDto response = new ResponseDto();
        String text = dto.getText();
        HuggingFaceClient.AIResult aiResult = huggingFaceClient.analyzeText(text);
        response.setSentiment(aiResult.getLabel());
        response.setConfidence(aiResult.getScore());
        System.out.println("Service hit");
        return response;
    }

}
