package com.jrp.pma.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.dto.ProjectStageCount;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;
import com.jrp.pma.springExample.Car;

@Controller
public class HomeController {
	
	@Autowired
	Car car;
	
//	@Value("${version}")
//	private String ver;
	
	@Autowired
	ProjectService proService;
	@Autowired
	EmployeeService empService;
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		
//		model.addAttribute("applicationVersion", ver);
		
		List<Project> projects = proService.getAll();
		model.addAttribute("projectList", projects);
		
		List<EmployeeProject> employeeProjectCnt = empService.employeeProjects();
		model.addAttribute("employeeListProjectCnt", employeeProjectCnt);
		
		List<ProjectStageCount> projectStageCnt = proService.projectStageCount();
		model.addAttribute("projectstagecnt", projectStageCnt);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectStageCnt);
		// [["NOTSTARTED", 1], ["INPROGRESS", 2], ["COMPLETED", 1]]
		model.addAttribute("projectStatusCnt", jsonString);
		
		return "home/home";
	}

}
