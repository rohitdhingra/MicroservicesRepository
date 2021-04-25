package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users",catalog="test")
@Getter
@Setter
@NoArgsConstructor
public class User {
	@Id
	private Integer id;
	private String name;
	private Long salary;
	private String teamName;
}
