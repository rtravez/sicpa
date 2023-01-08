package com.sicpa.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeeDto extends BaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String surname;
	@NotNull
	private Integer age;
	//@Email
	private String email;
	private String position;
	//private List<DepartmentEmployeeDto> departmentEmployees;
	//private List<UserDto> users;

}