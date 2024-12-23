package org.springframework.samples.petclinic.chat;

import dev.langchain4j.agent.tool.Tool;
import lombok.AllArgsConstructor;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.samples.petclinic.vet.VetRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@AllArgsConstructor
public class VetTools {

	private final VetRepository vetRepository;

	@Tool(value = { "return a list of Vets, include their specialism"})
	public Collection<Vet> getVetList() {
		return vetRepository.findAll();
	}
}
