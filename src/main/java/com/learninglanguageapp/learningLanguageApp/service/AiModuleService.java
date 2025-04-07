package com.learninglanguageapp.learningLanguageApp.service;

import com.learninglanguageapp.learningLanguageApp.dto.AiResponseDto;
import com.learninglanguageapp.learningLanguageApp.dto.AiResponseDtoConverter;
import com.learninglanguageapp.learningLanguageApp.dto.UserResponseDto;
import com.learninglanguageapp.learningLanguageApp.dto.UserTalkRequest;
import com.learninglanguageapp.learningLanguageApp.model.AiResponse;
import com.learninglanguageapp.learningLanguageApp.model.ConversationEntry;
import com.learninglanguageapp.learningLanguageApp.model.Education;
import com.learninglanguageapp.learningLanguageApp.model.UserLastTalkWithAi;
import com.learninglanguageapp.learningLanguageApp.repository.AiModuleRepository;
import com.learninglanguageapp.learningLanguageApp.repository.UserLastTalkWithAiRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AiModuleService {
    private final EducationService educationService;
    private final AiModuleRepository aiModuleRepository;
    private final UserLastTalkWithAiRepository userLastTalkWithAiRepository;
    private final AiResponseDtoConverter aiResponseDtoConverter;
    public AiModuleService(EducationService educationService, AiModuleRepository aiModuleRepository, UserLastTalkWithAiRepository userLastTalkWithAiRepository, AiResponseDtoConverter aiResponseDtoConverter) {
        this.educationService = educationService;
        this.aiModuleRepository = aiModuleRepository;
        this.userLastTalkWithAiRepository = userLastTalkWithAiRepository;
        this.aiResponseDtoConverter = aiResponseDtoConverter;
    }


    public String translateWords(String userMessage) {
       OpenAiService translate= new OpenAiService();
       return translate.deteminateRole("You are a language learning assistant users will ask you words they don't know and you will translate them into the desired language and you will only return the translated word you will not type anything else"
       ,userMessage);
    }

    public AiResponseDto talkToAi(UserTalkRequest userTalkRequest) {
        if (userTalkRequest.getEducationId() != null) {
            // Education objesini al
            Education education = educationService.getEduForExercise(userTalkRequest.getEducationId())
                    .orElseThrow(() -> new IllegalArgumentException("Education not found"));

            // Eğer bu education için konuşma var mı kontrol et
            boolean isAnyConversation = userLastTalkWithAiRepository.isAnyConversation(education);

            if (isAnyConversation) {
                // Önceki konuşmayı al
                UserLastTalkWithAi userLastTalkWithAi = userLastTalkWithAiRepository.findByEducationId(userTalkRequest.getEducationId());

                // lastConversation listesini kontrol et ve başlat
                if (userLastTalkWithAi.getLastConversation() == null) {
                    userLastTalkWithAi.setLastConversation(new ArrayList<>());
                }

                // AI ile konuşmayı başlat
                OpenAiService openAiService = new OpenAiService();
                String aiText = openAiService.talkToAi(userTalkRequest.getText(), userLastTalkWithAi.getLastConversation(), education.getEducationLevel());

                // Yeni konuşma girişini oluştur
                ConversationEntry newEntry = new ConversationEntry(userTalkRequest.getText(), aiText, userLastTalkWithAi);
                userLastTalkWithAi.getLastConversation().add(newEntry); // Doğrudan ekle

                // Güncellenmiş veriyi kaydet
                userLastTalkWithAiRepository.save(userLastTalkWithAi);

                // Yanıt döndür
                return aiResponseDtoConverter.convert(new AiResponse(userTalkRequest.getEducationId(), aiText));
            } else {
                // Yeni bir konuşma başlat
                UserLastTalkWithAi userLastTalkWithAi = new UserLastTalkWithAi();
                ConversationEntry conversationEntry1 = new ConversationEntry();
                conversationEntry1.setSender(userTalkRequest.getText());

                OpenAiService openAiService = new OpenAiService();
                String aiText = openAiService.talkToAi(userTalkRequest.getText(), List.of(conversationEntry1), education.getEducationLevel());
                conversationEntry1.setMessage(aiText);

                // lastConversation listesini kontrol et ve başlat
                if (userLastTalkWithAi.getLastConversation() == null) {
                    userLastTalkWithAi.setLastConversation(new ArrayList<>());
                }

                userLastTalkWithAi.getLastConversation().add(conversationEntry1); // Doğrudan ekle
                userLastTalkWithAi.setEducation(education);
                education.getUserLastTalkWithAi().add(userLastTalkWithAi);

                // Güncellenmiş veriyi kaydet
                educationService.updateEduForAi(education);
                userLastTalkWithAiRepository.save(userLastTalkWithAi);

                // Yanıt döndür
                return aiResponseDtoConverter.convert(new AiResponse(education.getId(), aiText));
            }
        }
        return new AiResponseDto(null);
    }



}
