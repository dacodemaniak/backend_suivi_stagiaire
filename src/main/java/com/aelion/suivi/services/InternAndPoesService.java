package com.aelion.suivi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aelion.suivi.dto.InternInputDto;
import com.aelion.suivi.entities.InternEntity;
import com.aelion.suivi.entities.POEEntity;
import com.aelion.suivi.repositories.InternRepository;
import com.aelion.suivi.repositories.POERepository;

@Service
public class InternAndPoesService implements ICrudInternAndPoes {

	@Autowired
	InternRepository internRepository;
	
	@Autowired
	POERepository poeRepository;
	
	@Override
	public InternEntity addInternAndPoes(InternInputDto input) {
		// Create an InternEntity Object (instanciate InternEntity class)
		InternEntity intern = new InternEntity();
		
		// Deserialize input to intern
		intern.setName(input.name);
		intern.setFirstName(input.firstName);
		intern.setEmail(input.email); // In the true life, we'll have to check email pattern
		intern.setAddress(input.address);
		intern.setBirthDate(input.birthDate); // In the true life, check that birthDate is before today
		intern.setPhoneNumber(input.phoneNumber);
		
		// Persists the brand new InternEntity
		this.internRepository.save(intern);
		
		// Let add the new Intern to each concerned poe of poes
		for (POEEntity poe : input.poes) {
			poe.addIntern(intern);
			// Dont't forget to save the POE updated
			this.poeRepository.save(poe);
		}

		return intern;
	}

}
