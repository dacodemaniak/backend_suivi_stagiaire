package com.aelion.suivi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aelion.suivi.dto.InternInputDto;
import com.aelion.suivi.entities.InternEntity;
import com.aelion.suivi.services.InternAndPoesService;

@RestController
@RequestMapping("/internandpoes")
public class InternAndPoesController {

	@Autowired
	InternAndPoesService internAndPoesService;
	
	@PostMapping()
	@CrossOrigin
	public InternEntity add(InternInputDto input) {
		return null;
		// Return a new instance of InternEntity
	}
	
}
