package com.aelion.suivi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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
	
	/**
	 * JPQL
	 * On travaille avec les attributs et les entités
	 * @param email
	 * @return
	 */
	@Query("SELECT i FROM InternEntity i WHERE i.email = :email")
	public InternEntity internByMail(@Param("email") String email);
	
	/**
	 * Native : on travaille avec le nom des tables et les colonnes
	 * @param email
	 * @return
	 */
	@Query(value="SELECT * FROM intern WHERE email = :email", nativeQuery=true)
	public InternEntity nativeInternByMail(@Param("email") String email);
	
	@Modifying
	@Transactional
	@Query(
			value="DELETE FROM interns_to_poes itp WHERE itp.intern_id = :id",
			nativeQuery = true
	)
	public void removeFromPOE(@Param("id") Long id);

}
