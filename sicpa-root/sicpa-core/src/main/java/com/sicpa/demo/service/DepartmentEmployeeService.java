package com.sicpa.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sicpa.demo.entity.DepartmentEmployee;
import com.sicpa.demo.exception.ExceptionManager;
import com.sicpa.demo.repository.IDepartmentEmployeeRepository;
import com.sicpa.demo.repository.IGenericRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * <b> Description de la class, interface o enumeration. </b>
 *
 * @author renetravez
 * @version $1.0$
 */
@Service
@Slf4j
public class DepartmentEmployeeService extends GenericService<DepartmentEmployee, Long>
		implements IDepartmentEmployeeService {

	@Autowired
	private IDepartmentEmployeeRepository departmentEmployeeRepository;

	@Autowired
	public DepartmentEmployeeService(
			@Qualifier("departmentEmployeeRepository") IGenericRepository<DepartmentEmployee, Long> genericRepository) {
		super(genericRepository);
	}

	@Override
	@Transactional(readOnly = true)
	public List<DepartmentEmployee> findDepartmentEmployeeAll() throws ExceptionManager {
		return departmentEmployeeRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<DepartmentEmployee> findDepartmentEmployeeById(Long id) {
		return departmentEmployeeRepository.findById(id);
	}
}
