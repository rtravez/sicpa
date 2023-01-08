package com.sicpa.demo.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.sicpa.demo.entity.Enterprise;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class EnterpriseRepository extends GenericRepository<Enterprise, Long> implements IEnterpriseRepository {
	public EnterpriseRepository(EntityManager em) {
		super(Enterprise.class, em);
	}
}
