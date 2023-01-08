package com.sicpa.demo.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.sicpa.demo.exception.ExceptionManager;

public interface IGenericService<T, ID extends Serializable> {

	T save(T entity) throws ExceptionManager;

	List<T> saveAll(List<T> entities) throws ExceptionManager;

	Optional<T> findById(ID id) throws ExceptionManager;

	boolean existsById(ID id) throws ExceptionManager;

	List<T> findAll() throws ExceptionManager;

	List<T> findAllById(List<ID> ids) throws ExceptionManager;

	Long count() throws ExceptionManager;

	void deleteById(ID id) throws ExceptionManager;

	void delete(T entity) throws ExceptionManager;

	void deleteAllById(List<ID> ids) throws ExceptionManager;

	void deleteAll(List<T> entities) throws ExceptionManager;
}
