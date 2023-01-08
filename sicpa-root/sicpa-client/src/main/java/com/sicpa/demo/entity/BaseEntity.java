package com.sicpa.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "created_host", updatable = false, length = 50, nullable = false)
    private String createdHost;

    @Column(name = "modified_host", updatable = false, length = 50)
    private String modifiedHost;

    @Column(name = "created_by", updatable = false, length = 50, nullable = false)
    private String createdBy;

    @Column(name = "modified_by", updatable = false, length = 50)
    private String modifiedBy;

    @Column(name = "created_date", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "modified_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @Column(name = "status", nullable = false)
	private Boolean status;

}
