package com.aelion.suivi.dto;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.aelion.suivi.entities.InternEntity;

public abstract class DtoMapper implements Mappable{
	public Object map(Object e, Object o) {
		ArrayList<Field> annotedFields = new ArrayList<>();
		// Récupérer les attributs décorés par @Map dans cette classe
		try {
			Field[] internFields = e.getClass().getDeclaredFields();
			
			Field[] fields = o.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Annotation annotation = fields[i].getAnnotation(Map.class);
				if (annotation != null) {
					for (int j = 0; j < internFields.length; j++) {
						if (fields[i].getName() == internFields[j].getName()) {
							//annotedFields.add(fields[i]);
							String getter = "get" + internFields[j].getName().substring(0, 1).toUpperCase() + internFields[j].getName().substring(1);
							Method method = e.getClass().getDeclaredMethod(getter, null);
							fields[i].set(this, method.invoke(e, null));
						}
					}
				}
			}
			return this;
			
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		} finally {
			return this;
		}

	}
}
