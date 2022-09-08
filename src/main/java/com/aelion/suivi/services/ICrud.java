/**
 * 
 */
package com.aelion.suivi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

/**
 * @author Aelion
 *
 */
public interface ICrud<T> {
	public T add(T t);
	public T[] add(T[] ts);
	public void update(T t);
	public void delete(T t);
	public ResponseEntity<?> delete(Long id) throws Exception;
	public List<T> findAll();
	public Optional<T> findOne(Long id);
}
