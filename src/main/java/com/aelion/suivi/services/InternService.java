/**
 * 
 */
package com.aelion.suivi.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aelion.suivi.entities.InternEntity;
import com.aelion.suivi.repositories.FakeInternRepository;
import com.aelion.suivi.repositories.InternRepository;


/**
 * @author Aelion
 *
 */
@Service
public class InternService implements ICrud<InternEntity> {

	private FakeInternRepository internRepository = new FakeInternRepository();
	
	@Autowired
	private InternRepository repository;
	
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
		// TODO Auto-generated method stub
	}

}
