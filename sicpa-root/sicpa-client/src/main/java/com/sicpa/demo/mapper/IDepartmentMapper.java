package com.sicpa.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.sicpa.demo.dto.DepartmentDto;
import com.sicpa.demo.entity.Department;


@Mapper(implementationName = "DepartmentMapper", implementationPackage = "<PACKAGE_NAME>.impl"/*, unmappedTargetPolicy = ReportingPolicy.IGNORE*/)
public interface IDepartmentMapper {

    DepartmentDto entityToDto(Department entity);

    Department dtoToEntity(DepartmentDto dto);

    List<DepartmentDto> entitiesToDtos(List<Department> entities);

    List<Department> dtosToEntities(List<DepartmentDto> dtos);
}
