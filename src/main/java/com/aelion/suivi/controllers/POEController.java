/**
 * 
 */
package com.aelion.suivi.controllers;

import java.util.ArrayList;
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

import com.aelion.suivi.dto.POEDto;
import com.aelion.suivi.entities.InternEntity;
import com.aelion.suivi.entities.POEEntity;
import com.aelion.suivi.services.POEService;
import com.aelion.suivi.services.exception.NotFoundException;

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
	public List<POEDto> findAll() {
		return this.service.fullPOE();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable int id) throws Exception {
		try {
			return ResponseEntity.ok(this.service.getOne((long) id));
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			//return ResponseEntity.notFound().build();
			
			return e.send();
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
