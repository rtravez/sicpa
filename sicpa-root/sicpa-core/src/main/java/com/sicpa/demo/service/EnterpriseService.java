package com.sicpa.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sicpa.demo.entity.Enterprise;
import com.sicpa.demo.repository.IEnterpriseRepository;
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
public class EnterpriseService extends GenericService<Enterprise, Long> implements IEnterpriseService {

	@Autowired
	private IEnterpriseRepository enterpriseRepository;

	@Autowired
	public EnterpriseService(
			@Qualifier("enterpriseRepository") IGenericRepository<Enterprise, Long> genericRepository) {
		super(genericRepository);
	}
}
