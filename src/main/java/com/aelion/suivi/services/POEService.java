/**
 * 
 */
package com.aelion.suivi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aelion.suivi.dto.POEDto;
import com.aelion.suivi.entities.InternEntity;
import com.aelion.suivi.entities.POEEntity;
import com.aelion.suivi.repositories.POERepository;
import com.aelion.suivi.services.exception.NotFoundException;

/**
 * @author Aelion
 *
 */
/**
 * @author jlaubert
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
	public void delete(Long id) throws Exception {
		Optional<POEEntity> oEntity = this.findOne(id);
		
		if (oEntity.isPresent()) {
			try {
				this.repository.deleteById(id.intValue());
			} catch(IllegalArgumentException e) {
				throw new IllegalArgumentException();
			}
		} else {
			throw new Exception("POE with id : " + id + " was not found!");
		}
		
		
	}

	@Override
	public List<POEEntity> findAll() {
		// TODO Auto-generated method stub
		return (List<POEEntity>) this.repository.findAll();
	}

//	public List<POEDto> fullPOE() {
//		List<POEEntity> poes = this.findAll();
//		List<POEDto> alDto = new ArrayList<>();
//		
//		for (POEEntity poe : poes) {
//			POEDto dto = new POEDto(poe);
//			dto.map(poe, dto);
//			
//			for (InternToPOEEntity internToPOE : poe.getInternsToPOE()) {
//				dto.addIntern(internToPOE.getIntern());
//			}
//			alDto.add(dto);
//		}
//		
//		return alDto;
//	}
	
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
