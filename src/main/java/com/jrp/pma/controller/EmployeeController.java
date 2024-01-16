package com.jrp.pma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.services.EmployeeService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	@GetMapping("/new")
	public String newEmployee(Model mdl) {
		Employee employee = new Employee();
		mdl.addAttribute("emp", employee);
		return "employees/new-employee";
	}
	
	@PostMapping("/save")
	public String addEmployee(@Valid @ModelAttribute("emp") Employee employee, BindingResult errors, Model model) {
		
		if(errors.hasErrors()) {
			model.addAttribute("emp", employee);
			return "employees/new-employee";
		}
		else {
			empService.save(employee);
			return "redirect:/employee";
		}
		
	}
	
	@GetMapping
	public String onlyEmployees(Model model) {
		Iterable<Employee> employees = empService.getAll();
		model.addAttribute("employees", employees);
		
		return "employees/employeesOnly";
	}
	
	@GetMapping("/update")
	public String displayEmployeeUpdateForm(@RequestParam("id") long empID, Model mdl) {
		Employee theEmp = empService.findByEmpID(empID);
		mdl.addAttribute("emp", theEmp);
		return "employees/new-employee";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") long empID, Model mdl) {
		Employee theEmp = empService.findByEmpID(empID);
		empService.delete(theEmp);
		return "redirect:/employee";
	}
	
}
