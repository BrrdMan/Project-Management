package com.jrp.pma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectService proService;
	@Autowired
	EmployeeService empService;
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		List<Employee> employees = empService.getAll();
		Project newProject = new Project();
		model.addAttribute("project", newProject);
		model.addAttribute("allEmployees", employees);
		
		return "project/new-project";
	}
	
	@PostMapping("/save")
	public String addProject(@Valid Project project, BindingResult br, Model model) {
		if(br.hasErrors()) {
			List<Employee> employees = empService.getAll();
			model.addAttribute("project", project);
			model.addAttribute("allEmployees", employees);
			
			return "project/new-project";
		}
		
		proService.save(project);
		return "redirect:/projects/new";
	}
	
	@GetMapping
	public String onlyProjects(Model model) {
		List<Project> projects = proService.getAll();
		model.addAttribute("projectList", projects);
		return "project/projectsOnly";
	}
	
	@GetMapping("/update")
	public String displayProjectUpdateForm(@RequestParam("id") long projId, Model mdl) {
		Project pro = proService.findByProjectId(projId);
		List<Employee> employees = empService.getAll();
		mdl.addAttribute("project", pro);
		mdl.addAttribute("allEmployees", employees);
		return "project/new-project";
	}
	
	@GetMapping("/delete")
	public String deleteProject(@RequestParam("id") long projId, Model mdl) {
		Project pro = proService.findByProjectId(projId);
		proService.delete(pro);
		return "redirect:/projects";
	}

}
