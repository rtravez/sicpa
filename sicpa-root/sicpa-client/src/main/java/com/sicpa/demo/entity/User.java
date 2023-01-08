package com.sicpa.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(nullable = false, length = 60)
	private String password;

	@Column(nullable = false, length = 20)
	private String username;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<RoleUser> roleUsers;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_employee", nullable = false)
	private Employee employee;

}