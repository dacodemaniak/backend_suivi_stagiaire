package com.aelion.suivi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.aelion.suivi.entities.InternEntity;

public interface InternRepository extends CrudRepository<InternEntity, Long> {
	
	/**
	 * delete(T t)
	 * DELETE FROM t.tableName WHERE id = object.id;
	 */

}
