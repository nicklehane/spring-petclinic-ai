package org.springframework.samples.petclinic.chat;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

	private final ChatLanguageModel chatLanguageModel;
	private final VetTools vetTools;
	private final OwnerTools ownerTools;;

	private Assistant assistant;

	public ChatService(ChatLanguageModel chatLanguageModel, VetTools vetTools, OwnerTools ownerTools) {

		this.chatLanguageModel = chatLanguageModel;
		this.vetTools = vetTools;
		this.ownerTools = ownerTools;

		assistant = AiServices.builder(Assistant.class)
			.chatLanguageModel(chatLanguageModel)
			.systemMessageProvider(chatMemoryId -> "You are a polite assistant at the Pet Clinic")
			.chatMemory(MessageWindowChatMemory.withMaxMessages(10))
			.tools(List.of(vetTools, ownerTools))
			.build();
	}

	public String getVetSummary() {

		var userMessage = "Please provide a brief description of all the vets who work at the clinic.";
		return assistant.chat(userMessage);
	}

	public String getOwnerSummary() {
		var userMessage = "Please provide a brief summary of all the owners registered with the clinic.";
		return assistant.chat(userMessage);
	}

	public String getIndividualOwnerSummary(Integer ownerId) {
		var userMessage = "Please provide a summary of the Owner with id " + ownerId + ".  Include details of " +
			"their Pets along with dates they have visited the clinic and any treatments they received";
		return assistant.chat(userMessage);
	}


}
