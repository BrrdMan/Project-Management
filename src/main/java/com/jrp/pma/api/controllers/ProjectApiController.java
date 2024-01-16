package com.jrp.pma.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jrp.pma.entities.Project;
import com.jrp.pma.services.ProjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {
	
	@Autowired
	ProjectService proServ;
	
	@GetMapping
	public List<Project> getProjects(){
		return proServ.getAll();
	}
	
	@GetMapping("/{id}")
	public Project findProject(@PathVariable("id") Long id) {
		return proServ.findById(id);
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void creteProject(@RequestBody @Valid Project pro) {
		proServ.save(pro);
	}
	
	@PutMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Project update(@RequestBody @Valid Project pro) {
		return proServ.save(pro);
	}
	
	@PatchMapping(path="/{id}", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Project partialUpdate(@PathVariable("id") Long id, @RequestBody @Valid Project patchPro) {
		Project pro = proServ.findById(id);
		
		if(patchPro.getName() != null) {
			pro.setName(patchPro.getName());
		}
		if(patchPro.getStage() != null) {
			pro.setStage(patchPro.getStage());
		}
		if(patchPro.getDesc() != null) {
			pro.setDesc(patchPro.getDesc());
		}
		
		return proServ.save(pro);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		try {
			proServ.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			
		}
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exc){
		Map<String, String> errors = new HashMap<>();
		
		exc.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		
		return errors;
	}

}
