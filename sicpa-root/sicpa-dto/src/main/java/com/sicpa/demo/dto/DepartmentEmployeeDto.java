package com.sicpa.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DepartmentEmployeeDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    @NotNull
    private DepartmentDto department;
    @NotNull
    private EmployeeDto employee;
}