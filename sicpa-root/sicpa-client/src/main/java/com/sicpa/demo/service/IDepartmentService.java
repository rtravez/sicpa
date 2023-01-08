package com.sicpa.demo.service;

import java.util.List;
import java.util.Optional;

import com.sicpa.demo.entity.Department;
import com.sicpa.demo.exception.ExceptionManager;

/**
 * <b> Description de la class, interface o enumeration. </b>
 *
 * @author renetravez
 * @version $1.0$
 */
public interface IDepartmentService extends IGenericService<Department, Long> {

	List<Department> findDepartmentAll() throws ExceptionManager;

	Optional<Department> findDepartmentById(Long id) throws ExceptionManager;
}
