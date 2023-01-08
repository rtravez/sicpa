package com.sicpa.demo.service;

import java.util.List;
import java.util.Optional;

import com.sicpa.demo.entity.DepartmentEmployee;
import com.sicpa.demo.exception.ExceptionManager;

/**
 * <b> Description de la class, interface o enumeration. </b>
 *
 * @author renetravez
 * @version $1.0$
 */
public interface IDepartmentEmployeeService extends IGenericService<DepartmentEmployee, Long> {

	List<DepartmentEmployee> findDepartmentEmployeeAll() throws ExceptionManager;

	Optional<DepartmentEmployee> findDepartmentEmployeeById(Long id);
}
