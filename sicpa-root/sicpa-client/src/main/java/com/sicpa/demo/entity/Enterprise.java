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
@Table(name = "enterprises")
@Data
@EqualsAndHashCode(callSuper = false)
public class Enterprise extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "phone", precision = 10)
	private String phone;

//	@OneToMany(mappedBy = "enterprise", fetch = FetchType.LAZY)
//	private List<Department> departments;
}
