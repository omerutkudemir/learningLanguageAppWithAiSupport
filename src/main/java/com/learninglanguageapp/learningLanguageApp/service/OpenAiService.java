package com.learninglanguageapp.learningLanguageApp.service;

import com.learninglanguageapp.learningLanguageApp.model.ConversationEntry;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
@Service
public class OpenAiService {
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private String apiKey;
    public String deteminateRole(String role,String userMessage)
    {
        Properties prop = new Properties();
        try (
                FileInputStream input = new FileInputStream("src/main/resources/application.properties")) {
            prop.load(input);
            apiKey = prop.getProperty("openai.api.key");
        } catch (IOException e) {
            e.printStackTrace();
        }
        RestTemplate restTemplate = new RestTemplate();

        // HTTP Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        // JSON Body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-4o"); // Kullanmak istediğiniz modeli seçin
        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "system", "content", role));
        messages.add(Map.of("role", "user", "content", userMessage));
        requestBody.put("messages", messages);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        // API çağrısını yap
        ResponseEntity<Map> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, Map.class);

        // Yanıtı al ve döndür
        List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");

        // Burada null kontrolü ve liste elemanının olup olmadığına bakıyoruz
        if (choices != null && !choices.isEmpty()) {
            Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");

            // "content" alanını döndür
            if (message != null) {
                return (String) message.get("content");
            } else {
                return "Message content not found.";
            }
        } else {
            return "No choices returned.";
        }
    }


    public String talkToAi(String currentUserText, List<ConversationEntry> lastConversation, String educationLevel) {
        Properties prop = new Properties();
        String apiKey = "";

        // API anahtarını yükle
        try (FileInputStream input = new FileInputStream("src/main/resources/application.properties")) {
            prop.load(input);
            apiKey = prop.getProperty("openai.api.key");
        } catch (IOException e) {
            e.printStackTrace();
            return "Error loading API key.";
        }

        RestTemplate restTemplate = new RestTemplate();

        // HTTP Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        // JSON Body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-4o-mini");  // Model adı güncellendi

        List<Map<String, String>> messages = new ArrayList<>();

        // Yapay zekanın rolünü belirle
        String aiRole = String.format("I want you to chat with me in English at %s level, open the topic and start following the conversations and give related answers.", educationLevel);
        messages.add(Map.of("role", "assistant", "content", aiRole));

        // Geçmiş konuşmaları ekle
        for (ConversationEntry entry : lastConversation) {
            String role = entry.getSender().equals("User") ? "user" : "assistant"; // "User" -> "user", "AI" -> "assistant"
            String content = entry.getMessage();
            // Eğer gönderilen mesaj AI'dan geldiyse, eğitim seviyesi bilgisini içeriğe ekle
            if (!entry.getSender().equals("User")) {
                content = String.format("I want you to chat with me in English at %s level, following the conversation: me (AI): %s and you (User): %s", educationLevel, entry.getMessage(), entry.getSender());
            }

            messages.add(Map.of(
                    "role", role,
                    "content", content
            ));

        }
        // Yeni kullanıcı mesajını ekle
        messages.add(Map.of("role", "user", "content", currentUserText));
        requestBody.put("messages", messages);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        // API çağrısını yap
        ResponseEntity<Map> response = restTemplate.exchange("https://api.openai.com/v1/chat/completions", HttpMethod.POST, entity, Map.class);

        // Yanıtı işle
        if (response.getBody() != null) {
            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");

            if (choices != null && !choices.isEmpty()) {
                Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");

                if (message != null) {
                    return (String) message.get("content"); // AI cevabını döndür
                }
            }
        }

        return "No response from AI.";
    }



}
