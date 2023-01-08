package com.sicpa.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "employees")
@Data
@EqualsAndHashCode(callSuper = false)
public class Employee extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "surname", nullable = false)
	private String surname;

	@Column(name = "age", nullable = false)
	private Integer age;

	//@Email
	@Column(name = "email")
	private String email;

	@Column(name = "position", nullable = false)
	private String position;

//	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
//	private List<DepartmentEmployee> departmentEmployees;
//
//	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
//	private List<User> users;

}
