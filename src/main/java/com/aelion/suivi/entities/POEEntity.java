package com.aelion.suivi.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity()
@Table(name="poe")
public class POEEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private Date beginDate;
	private Date endDate;
	
	@ManyToOne()
	private POETypeEntity poeType;
	

	@ManyToMany()
	@JoinTable(
			name="interns_to_poes",
			joinColumns=@JoinColumn(name="poe_id"), // FK
			inverseJoinColumns=@JoinColumn(name="intern_id") // FK
	)
	private List<InternEntity> interns = new ArrayList<>();
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the beginDate
	 */
	public Date getBeginDate() {
		return beginDate;
	}
	/**
	 * @param beginDate the beginDate to set
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the poeType
	 */
	public POETypeEntity getPoeType() {
		return poeType;
	}
	/**
	 * @param poeType the poeType to set
	 */
	public void setPoeType(POETypeEntity poeType) {
		this.poeType = poeType;
	}
	
	/**
	 * @return the interns
	 */
	public List<InternEntity> getInterns() {
		return this.interns;
	}
	
	public void addIntern(InternEntity intern) {
		this.interns.add(intern);
	}
}
