package com.sicpa.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EnterpriseDto extends BaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotEmpty
	private String address;
	@NotEmpty
	private String name;
	private String phone;
	//private List<DepartmentDto> departments;

}
