package com.sicpa.demo.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty
    @Size(max = 50)
    private String createdHost;

    @Size(max = 50)
    private String modifiedHost;

    @NotEmpty
    @Size(max = 50)
    private String createdBy;

    @Size(max = 50)
    private String modifiedBy;

    @NotNull
    private Date createdDate;

    private Date modifiedDate;

    @NotNull
	private Boolean status;
}
