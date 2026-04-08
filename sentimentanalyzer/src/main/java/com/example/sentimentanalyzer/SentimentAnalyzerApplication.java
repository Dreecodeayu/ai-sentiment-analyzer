package com.example.sentimentanalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class SentimentAnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentimentAnalyzerApplication.class, args);
	}

}
