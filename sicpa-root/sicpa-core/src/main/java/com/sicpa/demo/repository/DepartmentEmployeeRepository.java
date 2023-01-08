package com.sicpa.demo.repository;

import static com.sicpa.demo.entity.QDepartment.department;
import static com.sicpa.demo.entity.QDepartmentEmployee.departmentEmployee;
import static com.sicpa.demo.entity.QEmployee.employee;
import static com.sicpa.demo.entity.QEnterprise.enterprise;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.sicpa.demo.entity.DepartmentEmployee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class DepartmentEmployeeRepository extends GenericRepository<DepartmentEmployee, Long>
		implements IDepartmentEmployeeRepository {

	public DepartmentEmployeeRepository(EntityManager em) {
		super(DepartmentEmployee.class, em);
	}

	@Override
	public List<DepartmentEmployee> findAll() {
		return queryFactory.selectFrom(departmentEmployee).innerJoin(departmentEmployee.employee, employee).fetchJoin()
				.innerJoin(departmentEmployee.department, department).fetchJoin()
				.innerJoin(department.enterprise, enterprise).fetchJoin().where(departmentEmployee.status.isTrue())
				.fetch();
	}

	@Override
	public Optional<DepartmentEmployee> findById(Long id) {
		return Optional
				.ofNullable(queryFactory.selectFrom(departmentEmployee).innerJoin(departmentEmployee.employee, employee)
						.fetchJoin().innerJoin(departmentEmployee.department, department).fetchJoin()
						.innerJoin(department.enterprise, enterprise).fetchJoin()
						.where(departmentEmployee.status.isTrue().and(departmentEmployee.id.eq(id))).fetchOne());
	}

}
