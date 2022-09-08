package com.aelion.suivi.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.aelion.suivi.entities.InternToPOEEntity;

public interface InternToPOERepository extends CrudRepository<InternToPOEEntity, Long> {
	@Query(
			value="DELETE FROM poe_interns pi WHERE pi.interns_id=:internId",
			nativeQuery=true
	)
	public void deleteAllInternToPOE(@Param("internId") Long id);
}
