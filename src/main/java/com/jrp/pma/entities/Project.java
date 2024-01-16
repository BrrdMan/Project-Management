package com.jrp.pma.entities;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
//@Table(name= "project_")
public class Project {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="project_generator")
	@SequenceGenerator(name = "project_generator", sequenceName = "project_seq", allocationSize = 1)
	@Column(name="project_id")
	private long projectId;
	
	@NotBlank(message = "Please enter project name")
	@Size(min=5, max=100, message = "Should be atleast 5 characters long")
	@Column(name="name")
	private String name;
	
	@NotBlank(message = "Please enter completion stage")
	@Size(min=5, max=20, message = "Should be atleast 5 characters long")
	@Column(name="stage")
	private String stage;
	
	@NotBlank(message = "Please enter a description")
	@Size(min=5, max=300, message = "Should be atleast 5 characters long")
	@Column(name="description")
	private String desc;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, 
			   fetch = FetchType.LAZY)
	@JoinTable(name="project_employee",
			   joinColumns=@JoinColumn(name="project_id"),
			   inverseJoinColumns=@JoinColumn(name="employee_id"))
	@JsonIgnore
	private List<Employee> employees;
	
	public Project() {
		
	}
	
	public Project(String name, String stage, String desc) {
		super();
		this.name = name;
		this.stage = stage;
		this.desc = desc;
	}
	
	
	public long getProjectId() {
		return projectId;
	}

	public String getName() {
		return name;
	}

	public String getStage() {
		return stage;
	}

	public String getDesc() {
		return desc;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	// convenience method

	public void addEmployee(Employee emp) {
		if(employees == null) {
			employees = new ArrayList<>();
		}
		employees.add(emp);
	}
}
