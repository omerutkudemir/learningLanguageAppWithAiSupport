package com.learninglanguageapp.learningLanguageApp;

import com.learninglanguageapp.learningLanguageApp.model.*;
import com.learninglanguageapp.learningLanguageApp.repository.EducationRepository;
import com.learninglanguageapp.learningLanguageApp.service.AiModuleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class LearningLanguageAppApplication implements CommandLineRunner {
private final EducationRepository educationRepository;

    public LearningLanguageAppApplication(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(LearningLanguageAppApplication.class, args);
		//AiModuleService openAIService = new AiModuleService();
		//String response = openAIService.chatWithGPT("elma");
		//System.out.println("GPT Yanıtı: " + response);
	}


	@Override
	public void run(String... args) throws Exception {
		Education education = new Education(UUID.randomUUID(),null,"A1","Nouns",null,null,null);
		Language language = new Language("English");
		education.setLanguage(language);
		Exercise exercise= new Exercise();
		exercise.setFinished(true);
		Content content = new Content(UUID.randomUUID(),"İngilizcede isimler 3 hale ayrılır",null,exercise);
		List<Content> contentList=Arrays.asList(content);
		exercise.setContents(contentList);
		content.setExercise(exercise);
		List<Exercise> exerciseList =Arrays.asList(exercise);
		exercise.setEducation(education);
		education.setExercise(exerciseList);

		Education education1 = new Education(UUID.randomUUID(),null,"A1","werbs",null,null,null);
		Language language1 = new Language("French");
		education1.setLanguage(language1);
		Exercise exercise1= new Exercise();
		exercise1.setFinished(true);
		Content content1 = new Content(UUID.randomUUID(),"İngilizcede fiiller 3 hale ayrılır",null,exercise1);
		List<Content> contentList1=Arrays.asList(content1);
		exercise1.setContents(contentList1);
		content1.setExercise(exercise1);
		List<Exercise> exerciseList1 =Arrays.asList(exercise1);
		exercise1.setEducation(education1);
		education1.setExercise(exerciseList1);

		educationRepository.save(education);
		educationRepository.save(education1);
	}
}
