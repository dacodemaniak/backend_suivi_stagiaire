/**
 * 
 */
package com.aelion.suivi.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aelion.suivi.dto.InternInputDto;
import com.aelion.suivi.dto.InternShortListDto;
import com.aelion.suivi.entities.InternEntity;
import com.aelion.suivi.entities.POEEntity;
import com.aelion.suivi.repositories.FakeInternRepository;
import com.aelion.suivi.repositories.InternRepository;
import com.aelion.suivi.repositories.POERepository;


/**
 * @author Aelion
 *
 */
@Service
public class InternService implements ICrud<InternEntity> {

	private FakeInternRepository internRepository = new FakeInternRepository();
	
	@Autowired
	private InternRepository repository;
	
	@Autowired
	private POERepository poeRepository;
	
	/**
	 * INSERT INTO intern (name, firstName, ...., address) VALUES (...);
	 */
	@Override
	public InternEntity add(InternEntity intern) {
		// TODO Auto-generated method stub
		return this.repository.save(intern);
	}

	@Override
	public InternEntity[] add(InternEntity[] ts) {
		// TODO Auto-generated method stub
		return null;
	}

	public InternEntity addInternAndPoes(InternInputDto internDto) {
		InternEntity intern = new InternEntity();
		intern.setAddress(internDto.address);
		intern.setBirthDate(internDto.birthDate);
		intern.setEmail(internDto.email);
		intern.setFirstName(internDto.firstName);
		intern.setName(internDto.name);
		intern.setPhoneNumber(internDto.phoneNumber);
		
		// Persists intern
		this.repository.save(intern);
		
		// Persists POEs with the new Intern
		internDto.poes.forEach(inputPoe -> {
			Optional<POEEntity> oPoe = this.poeRepository.findById(inputPoe.getId());
			if (oPoe.isPresent()) {
				POEEntity poe = oPoe.get();
				
				poe.addIntern(intern);
				
				this.poeRepository.save(poe);
			}
		});
		return intern;
	}
	
	@Override
	public void update(InternEntity t) {
		this.repository.save(t);
		
	}

	@Override
	public void delete(InternEntity t) {
		// TODO Auto-generated method stub
		this.repository.delete(t);
	}

	@Override
	public List<InternEntity> findAll() {
		// TODO Auto-generated method stub
		//return (List<InternEntity>) this.repository.findAll();
		
		Iterable<InternEntity> itInterns = this.repository.findAll();
		
		Iterator<InternEntity> itrInterns = itInterns.iterator();
		
		ArrayList<InternEntity> alInterns = new ArrayList<>();
		
		while(itrInterns.hasNext()) {
			alInterns.add(itrInterns.next());
		}
		return alInterns;
	}
	
	/**
	 * 
	 * @return A List of InternShortListDto objects
	 */
	public List<InternShortListDto> shortList() {
		ArrayList<InternEntity> itEntity = (ArrayList<InternEntity>) this.repository.findAll();
		
		// Need to map InternEntity to InternShortListDto
		ArrayList<InternShortListDto> dto = new ArrayList<>();
		
		for(InternEntity entity : itEntity) {
			InternShortListDto transformed = new InternShortListDto(entity);
			dto.add(transformed);
		}
		
		return dto;
		/**
		 * for (const entity: InternEntity of itEntity)
		 */
	}

	public Iterable<InternEntity> internsAsIterable() {
		Iterable<InternEntity> itEntities =  this.repository.findAll();
		
		return itEntities;
	}
	
	@Override
	public Optional<InternEntity> findOne(Long id) {
		// TODO Auto-generated method stub
		
		return this.repository.findById(id);
	}
	
	public InternEntity getOne(Long id) {
		return this.internRepository.getOne(id);
	}

	@Override
	public void delete(Long id) {
		this.repository.removeFromPOE(id);
		this.repository.deleteById(id);
	}
	
	public List<InternEntity> findByName(String name) {
		return this.repository.findByName(name);
	}
	
	public ResponseEntity<?> byEmail(String email) {
		ResponseEntity response = null;
		
		InternEntity entity =  this.repository.internByMail(email);
		
		if (entity == null) {
			return new ResponseEntity(HttpStatus.OK);
		}
		
		return new ResponseEntity(HttpStatus.FORBIDDEN);
	}
	
	public boolean emailExists(String email) {
		return this.repository.internByMail(email) == null ? false : true;
	}

}
