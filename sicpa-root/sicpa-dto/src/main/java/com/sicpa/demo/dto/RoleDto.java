package com.sicpa.demo.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotEmpty
    @Size(max = 20)
    private String name;
    private List<RoleUserDto> roleUsers;
}