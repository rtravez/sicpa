package com.sicpa.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "departments")
@Data
@EqualsAndHashCode(callSuper = false)
public class Department extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "phone", precision = 10)
	private String phone;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_enterprise", nullable = false)
	private Enterprise enterprise;

//	@OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
//	private List<DepartmentEmployee> departmentEmployees;
}
