package com.sicpa.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sicpa.demo.entity.Department;
import com.sicpa.demo.exception.ExceptionManager;
import com.sicpa.demo.repository.IDepartmentRepository;
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
public class DepartmentService extends GenericService<Department, Long> implements IDepartmentService {

	@Autowired
	private IDepartmentRepository departmentRepository;

	@Autowired
	public DepartmentService(
			@Qualifier("departmentRepository") IGenericRepository<Department, Long> genericRepository) {
		super(genericRepository);
	}

	@Override
	public List<Department> findDepartmentAll() throws ExceptionManager {
		return departmentRepository.findAll();
	}

	@Override
	public Optional<Department> findDepartmentById(Long id) throws ExceptionManager {
		return departmentRepository.findById(id);
	}
}
