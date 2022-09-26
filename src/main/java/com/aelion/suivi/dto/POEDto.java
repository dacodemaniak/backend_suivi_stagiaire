package com.aelion.suivi.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aelion.suivi.entities.InternEntity;
import com.aelion.suivi.entities.POEEntity;

public class POEDto extends DtoMapper {
	private POEEntity entity;
	
	@Map()
	public int id;
	
	@Map()
	public String name;
	
	@Map()
	public Date beginDate;
	
	@Map()
	public Date endDate;
	
	public List<InternEntity> interns = new ArrayList<>();
	
	public POEDto(POEEntity entity) {
		this.entity = entity;
	}
	
	public void addIntern(InternEntity intern) {
		this.interns.add(intern);
	}
	
	public List<InternEntity> getInterns() {
		return this.interns;
	}
	
}
