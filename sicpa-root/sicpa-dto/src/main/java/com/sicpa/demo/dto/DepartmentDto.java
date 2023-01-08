package com.sicpa.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DepartmentDto extends BaseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	@NotEmpty
	private String description;
	@NotEmpty
	private String name;
	private String phone;
	@NotNull
	private EnterpriseDto enterprise;
	//private List<DepartmentEmployeeDto> departmentEmployees;
	
}
