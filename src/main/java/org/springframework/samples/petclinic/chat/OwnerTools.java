package org.springframework.samples.petclinic.chat;

import dev.langchain4j.agent.tool.Tool;
import lombok.AllArgsConstructor;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class OwnerTools {

	private final OwnerRepository ownerRepository;

	@Tool(value = {	"Return a list of all known owners and provide a summary of their details."	})
	List<Owner> summariseAllOwners() {
		return ownerRepository.findAll();
	}

	@Tool(value = {
		"Provide a summary of an individual owner, including details of their pets and any dates they've visited " +
			"the clinic along with any treatment they received."
	})
	Owner summariseOwner(Integer ownerId) {
		return ownerRepository.findById(ownerId).orElse(null);
	}
}
