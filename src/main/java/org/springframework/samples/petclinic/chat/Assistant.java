package org.springframework.samples.petclinic.chat;

import org.springframework.stereotype.Component;

@Component
public interface Assistant {

	String chat(String userMessage);
}
