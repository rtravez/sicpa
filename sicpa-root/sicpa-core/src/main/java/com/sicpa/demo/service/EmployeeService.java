package com.sicpa.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sicpa.demo.entity.Employee;
import com.sicpa.demo.repository.IEmployeeRepository;
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
public class EmployeeService extends GenericService<Employee, Long> implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(@Qualifier("employeeRepository") IGenericRepository<Employee, Long> genericRepository) {
        super(genericRepository);
    }
}
