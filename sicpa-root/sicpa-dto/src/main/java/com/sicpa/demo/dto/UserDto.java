package com.sicpa.demo.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotEmpty
	@Size(max = 60)
	private String password;
	@NotEmpty
	@Size(max = 20)
	private String username;
	private List<RoleUserDto> roleUsers;
	@NotNull
	private EmployeeDto employee;
}