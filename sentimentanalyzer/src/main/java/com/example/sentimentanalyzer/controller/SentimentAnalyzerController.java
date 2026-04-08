package com.example.sentimentanalyzer.controller;

import com.example.sentimentanalyzer.dto.RequestDto;
import com.example.sentimentanalyzer.dto.ResponseDto;
import com.example.sentimentanalyzer.service.SentimentAnalyzerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class SentimentAnalyzerController {

    private final SentimentAnalyzerService sentimentanalyzerService;
    public SentimentAnalyzerController(SentimentAnalyzerService sentimentanalyzerService){
        this.sentimentanalyzerService = sentimentanalyzerService;
    }
    @PostMapping("/analyze")
    public ResponseDto analyze(@RequestBody RequestDto userdto){
        System.out.println("Controller hit");
        return sentimentanalyzerService.analyze(userdto);
    }

}
