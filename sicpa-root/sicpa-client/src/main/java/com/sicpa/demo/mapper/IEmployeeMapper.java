package com.sicpa.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.sicpa.demo.dto.EmployeeDto;
import com.sicpa.demo.entity.Employee;

@Mapper(implementationName = "EmployeeMapper", implementationPackage = "<PACKAGE_NAME>.impl"/*
																							 * , unmappedTargetPolicy =
																							 * ReportingPolicy.IGNORE
																							 */)
public interface IEmployeeMapper {

	//@Mapping(target = "users", ignore = true)
	EmployeeDto entityToDto(Employee entity);

	Employee dtoToEntity(EmployeeDto dto);

	List<EmployeeDto> entitiesToDtos(List<Employee> entities);

	List<Employee> dtosToEntities(List<EmployeeDto> dtos);
}
