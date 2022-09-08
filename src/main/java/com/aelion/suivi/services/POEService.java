/**
 * 
 */
package com.aelion.suivi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aelion.suivi.entities.POEEntity;
import com.aelion.suivi.repositories.POERepository;
import com.aelion.suivi.services.exception.NotFoundException;

/**
 * @author Aelion
 *
 */
@Service
public class POEService implements ICrud<POEEntity> {
	
	@Autowired
	private POERepository repository;
	
	@Override
	public POEEntity add(POEEntity t) {
		// TODO Auto-generated method stub
		return this.repository.save(t);
	}

	@Override
	public POEEntity[] add(POEEntity[] ts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(POEEntity t) {
		// TODO Auto-generated method stub
		this.repository.save(t);
	}

	@Override
	public void delete(POEEntity t) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public ResponseEntity<?> delete(Long id) throws Exception {
		return null;
	}

	@Override
	public List<POEEntity> findAll() {
		// TODO Auto-generated method stub
		return (List<POEEntity>) this.repository.findAll();
	}

	@Override
	public Optional<POEEntity> findOne(Long id) {
		// TODO Auto-generated method stub
		return this.repository.findById(id.intValue());
		
	}
	
	public POEEntity getOne(Long id) throws Exception {
		Optional<POEEntity> oEntity = this.repository.findById(id.intValue());
		
		if (oEntity.isPresent()) {
			return oEntity.get();
		} else {
			throw new NotFoundException("the POE with " + id + " not found");	
		}
	}

}
