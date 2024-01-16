package com.jrp.pma.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jrp.pma.validators.UniqueValue;

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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
//@Table(name= "employee_")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="employee_generator")
	@SequenceGenerator(name = "employee_generator", sequenceName = "employee_seq", allocationSize = 1)
	@Column(name="employee_id")
	private long empID;
	
	@NotBlank(message = "Please enter your first name")
	@Pattern(regexp = "^[a-zA-Z]{2,30}$", message = "Firstname must be of 6 to 12 length with no special characters")
	@Column(name="first_name")
	private String firstName;
	
	@NotBlank(message = "Please enter your last name")
	@Pattern(regexp = "^[a-zA-Z]{2,30}$", message = "Lastname must be of 6 to 12 length with no special characters")
	@Column(name="last_name")
	private String lastName; 
	
	@NotBlank(message = "Please select your gender")
	@Column(name="gender")
	private String gender;
	
	@Email(message = "Please enter a valid email address")
	@NotBlank(message = "Please enter your email")
	@UniqueValue
	@Column(name="email")
	private String email;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, 
			   fetch = FetchType.LAZY)
	@JoinTable(name="project_employee",
	   		   joinColumns=@JoinColumn(name="employee_id"),
	           inverseJoinColumns=@JoinColumn(name="project_id"))
	@JsonIgnore
	private List<Project> projects;
	
	public Employee() {
		
	}
	
	public Employee(String firstName, String lastName, String gender, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
	}

	public long getEmpID() {
		return empID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getGender() {
		return gender;
	}

	public String getEmail() {
		return email;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setEmpID(long empID) {
		this.empID = empID;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
}
