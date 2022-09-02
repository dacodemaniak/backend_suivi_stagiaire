package com.aelion.suivi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name="interns_to_poes")
public class InternToPOEEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private InternEntity intern;
	
	
	@ManyToOne
	private POEEntity poe;

	/**
	 * @param intern the intern to set
	 */
	public void setIntern(InternEntity intern) {
		this.intern = intern;
	}
	
	/**
	 * @param poe the poe to set
	 */
	public void setPoe(POEEntity poe) {
		this.poe = poe;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the intern
	 */
	public InternEntity getIntern() {
		return intern;
	}

	/**
	 * @return the poe
	 */
	public POEEntity getPoe() {
		return poe;
	}
	
	
}
