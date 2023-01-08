package com.sicpa.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.sicpa.demo.dto.DepartmentEmployeeDto;
import com.sicpa.demo.entity.DepartmentEmployee;


@Mapper(implementationName = "DepartmentEmployeeMapper", implementationPackage = "<PACKAGE_NAME>.impl"/*, unmappedTargetPolicy = ReportingPolicy.IGNORE*/)
public interface IDepartmentEmployeeMapper {

    DepartmentEmployeeDto entityToDto(DepartmentEmployee entity);

    DepartmentEmployee dtoToEntity(DepartmentEmployeeDto dto);

    List<DepartmentEmployeeDto> entitiesToDtos(List<DepartmentEmployee> entities);

    List<DepartmentEmployee> dtosToEntities(List<DepartmentEmployeeDto> dtos);
}
