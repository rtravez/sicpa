package com.sicpa.demo.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.sicpa.demo.entity.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class EmployeeRepository extends GenericRepository<Employee, Long> implements IEmployeeRepository {

	public EmployeeRepository(EntityManager em) {
		super(Employee.class, em);
	}
}
