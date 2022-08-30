/**
 * 
 */
package com.aelion.suivi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aelion.suivi.entities.POEEntity;
import com.aelion.suivi.services.POEService;

/**
 * @author Aelion
 *
 */
@RestController
@RequestMapping("/poe")
public class POEController {
	
	@Autowired
	private POEService service;
	
	@GetMapping()
	public List<POEEntity> findAll() {
		return this.service.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable int id) {
		try {
			return ResponseEntity.ok(this.service.getOne((long) id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//return ResponseEntity.notFound().build();
			ResponseEntity<String> response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
			return response;
		}
	}
	
	@PostMapping()
	public POEEntity add(@RequestBody POEEntity poe) {
		return this.service.add(poe);
	}
	
	@PutMapping()
	public ResponseEntity<?> update(@RequestBody POEEntity poe) {
		this.service.update(poe);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * DELETE http://127.0.0.1/poe/999
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		try {
			this.service.delete((long)id);
			return ResponseEntity.noContent().build();
		} catch(IllegalArgumentException e) {
			return new ResponseEntity<String>("Id was not provided", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
}
