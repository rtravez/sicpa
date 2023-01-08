package com.sicpa.demo.repository;

import static com.sicpa.demo.entity.QDepartment.department;
import static com.sicpa.demo.entity.QEnterprise.enterprise;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.sicpa.demo.entity.Department;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class DepartmentRepository extends GenericRepository<Department, Long> implements IDepartmentRepository {
	public DepartmentRepository(EntityManager em) {
		super(Department.class, em);
	}

	@Override
	public List<Department> findAll() {
		return queryFactory.selectFrom(department).innerJoin(department.enterprise, enterprise).fetchJoin()
				.where(department.status.isTrue()).fetch();
	}

	@Override
	public Optional<Department> findById(Long id) {
		return Optional.ofNullable(queryFactory.selectFrom(department).innerJoin(department.enterprise, enterprise)
				.fetchJoin().where(department.status.isTrue().and(department.id.eq(id))).fetchOne());
	}

}
