/**
 * 
 */
package com.aelion.suivi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aelion.suivi.dto.InternShortListDto;
import com.aelion.suivi.entities.InternEntity;
import com.aelion.suivi.services.InternService;
import com.aelion.suivi.services.exception.NotPermittedException;

/**
 * @author Aelion
 *
 */
@RestController
@RequestMapping("/intern")
public class InternController {
	
	@Autowired
	private InternService internService;
	
	@GetMapping("/hello")
	public ResponseEntity<String> greetings() {
		return ResponseEntity.ok("Hello SpringBoot");
	}
	
	@GetMapping()
	@CrossOrigin
	public List<InternEntity> getAll() {
		return this.internService.findAll();
	}
	
	@GetMapping("/shortlist")
	public List<InternShortListDto> shortList() {
		return this.internService.shortList();
	}
	
	@GetMapping("/asiterable")
	public Iterable<InternEntity> findAll() {
		return this.internService.internsAsIterable();
	}
	
	@GetMapping("/{id}")
	@CrossOrigin
	public ResponseEntity<?> getOne(@PathVariable Long id) {
		Optional<InternEntity> oInternEntity = this.internService.findOne(id);
		
		if (oInternEntity.isPresent()) {
			return ResponseEntity.ok(oInternEntity.get());
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	/**
	 * 
	 * @param intern
	 * @return 201 http-status
	 */
	@PostMapping()
	public InternEntity add(@RequestBody InternEntity intern) {
		return this.internService.add(intern);
	}
	
	@DeleteMapping("/{id}")
	@CrossOrigin
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			this.internService.delete(id);
		} catch (NotPermittedException e) {
			System.out.println("Never");
		} finally {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PutMapping()
	public ResponseEntity<?> update(@RequestBody InternEntity intern) {
		this.internService.update(intern);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/byname/{name}")
	public List<InternEntity> findByName(@PathVariable String name) {
		return this.internService.findByName(name);
	}
	
	@GetMapping("/byemail")
	public ResponseEntity<?> byEmail(@RequestParam() String email) {
		return this.internService.byEmail(email);
	}
}
