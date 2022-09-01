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

/**
 * @author Aelion
 *
 */
public class InternShortListDto {
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
	
	public InternShortListDto transform(InternEntity intern) {
		this.id = intern.getId();
		this.name = intern.getName();
		this.firstName = intern.getFirstName();
		this.birthDate = intern.getBirthDate();
		
		return this;
	}
	
	public InternShortListDto map(InternEntity intern) {
		ArrayList<Field> annotedFields = new ArrayList<>();
		// Récupérer les attributs décorés par @Map dans cette classe
		try {
			Field[] internFields = intern.getClass().getDeclaredFields();
			
			Field[] fields = this.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Annotation annotation = fields[i].getAnnotation(Map.class);
				if (annotation != null) {
					for (int j = 0; j < internFields.length; j++) {
						if (fields[i].getName() == internFields[j].getName()) {
							//annotedFields.add(fields[i]);
							String getter = "get" + internFields[j].getName().substring(0, 1).toUpperCase() + internFields[j].getName().substring(1);
							Method method = intern.getClass().getDeclaredMethod(getter, null);
							fields[i].set(this, method.invoke(intern, null));
						}
					}
				}
			}
			return this;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return this;
		}

	}
}
