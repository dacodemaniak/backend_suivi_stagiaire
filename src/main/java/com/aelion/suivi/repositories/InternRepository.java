package com.aelion.suivi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.aelion.suivi.entities.InternEntity;

public interface InternRepository extends CrudRepository<InternEntity, Long> {
	
	/**
	 * 
	 * DELETE FROM intern WHERE intern.id = 1; // delete(t)
	 * SELECT * FROM intern; // findAll()
	 * SELECT * FROM intern WHERE intern.id = 1; // findOne(1)
	 * 
	 * INSERT INTO intern 
	 * 	(name, first_name, birth_date, email, phone_number, address)
	 * VALUES
	 * 	('Tartempion', 'Jean', '1989-10-22', 'j.tartempion@gmail.com', '05', '10');
	 * 
	 * UPDATE intern SET name='Van Dame', first_name='Jean-Claude',
	 * 	birth_date='1963-04-15', email='jcvd@aware.com', phone_number='06',
	 * 	address='Anvers' WHERE id = 4;
	 */
	
	public List<InternEntity> findByName(String name);
	public List<InternEntity> findByFirstName(String firstName);
	

}
