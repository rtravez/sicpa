package com.sicpa.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.sicpa.demo.dto.EnterpriseDto;
import com.sicpa.demo.entity.Enterprise;


@Mapper(implementationName = "EnterpriseMapper", implementationPackage = "<PACKAGE_NAME>.impl"/*, unmappedTargetPolicy = ReportingPolicy.IGNORE*/)
public interface IEnterpriseMapper {

    EnterpriseDto entityToDto(Enterprise entity);

    Enterprise dtoToEntity(EnterpriseDto dto);

    List<EnterpriseDto> entitiesToDtos(List<Enterprise> entities);

    List<Enterprise> dtosToEntities(List<EnterpriseDto> dtos);
}
