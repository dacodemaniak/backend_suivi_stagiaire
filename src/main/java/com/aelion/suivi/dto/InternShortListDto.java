/**
 * 
 */
package com.aelion.suivi.dto;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aelion.suivi.entities.InternEntity;
import com.aelion.suivi.entities.POEEntity;

/**
 * @author Aelion
 *
 */
public class InternShortListDto extends DtoMapper {
	private InternEntity entity;
	
	@Map() // Column in entity is id => getId()
	public Long id;
	
	@Map()
	public String name;
	
	@Map()
	public String firstName;
	
	@Map()
	public Date birthDate;
	
	@Map()
	public String address;
	
	public InternShortListDto(InternEntity entity) {
		this.entity = entity;
		this.map(this, entity);
	}
}
