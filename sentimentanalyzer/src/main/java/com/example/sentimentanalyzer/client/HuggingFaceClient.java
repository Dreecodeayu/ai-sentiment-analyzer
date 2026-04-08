    package com.example.sentimentanalyzer.client;

    import com.fasterxml.jackson.core.type.TypeReference;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.http.HttpEntity;
    import org.springframework.stereotype.Component;

    import org.springframework.http.HttpHeaders;
    import org.springframework.web.client.RestTemplate;

    import java.util.HashMap;
    import java.util.Map;
    import java.util.*;

    import static org.springframework.http.HttpMethod.POST;

    @Component
    public class HuggingFaceClient {

        public static class AIResult{
            String label;
            Double score;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public Double getScore() {
                return score;
            }

            public void setScore(Double score) {
                this.score = score;
            }
        }
        @Value("${huggingface.api.key}")
        private String apiKey;
        public AIResult analyzeText(String text) {
            Map<String, String> body = new HashMap<>();
            body.put("inputs", text);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiKey);
            headers.set("Content-Type", "application/json");
            headers.set("Accept", "application/json");
            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
            System.out.println("Client method called");
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://router.huggingface.co/hf-inference/models/distilbert/distilbert-base-uncased-finetuned-sst-2-english";
            ObjectMapper mapper = new ObjectMapper();
            try {
                System.out.println("About to call HuggingFace API");
                String responseString = restTemplate.exchange(url, POST, request, String.class).getBody();
                System.out.println(responseString);
                List<List<AIResult>> outerList = mapper.readValue(responseString,
                        new TypeReference<List<List<AIResult>>>() {});

                if (outerList == null || outerList.isEmpty() || outerList.get(0).isEmpty()){
                    return getDefaultResult();
                }

                return outerList.get(0).get(0);
            }catch(Exception e){
                e.printStackTrace();
                return getDefaultResult();
            }

        }
        private AIResult getDefaultResult() {
            AIResult result = new AIResult();
            result.setLabel("UNKNOWN");
            result.setScore(0.0);
            return result;
        }
    }
