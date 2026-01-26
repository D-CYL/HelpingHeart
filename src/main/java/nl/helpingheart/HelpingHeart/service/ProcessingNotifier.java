package nl.helpingheart.HelpingHeart.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProcessingNotifier {

    private static final String PROCESSING_URL = "http://localhost:9000/event";

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendEvent(String keuze) {
        try {
            Map<String, Object> body = new HashMap<>();
            body.put("keuze", keuze);
            body.put("timestamp", LocalDateTime.now().toString());

            restTemplate.postForObject(PROCESSING_URL, body, String.class);

            System.out.println("📡 Sent event to Processing: " + keuze);
        } catch (Exception e) {
            System.out.println("⚠️ Could not reach Processing (is it running?)");
        }
    }
}
