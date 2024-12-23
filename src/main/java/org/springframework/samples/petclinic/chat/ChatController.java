package org.springframework.samples.petclinic.chat;

import dev.langchain4j.model.chat.ChatLanguageModel;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ChatController {

	private ChatLanguageModel chatLanguageModel;

	@GetMapping("/ai/chat")
	String chat(@RequestParam(defaultValue = "What did Gandalf say to the Balrog?") String message) {
		return chatLanguageModel.generate(message);
	}
}
