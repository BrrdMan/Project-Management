package com.jrp.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.ProjectStageCount;
import com.jrp.pma.entities.Project;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository proRepo;
	
	public Project save(Project project) {
		return proRepo.save(project);
	}
	
	public List<Project> getAll(){
		return proRepo.findAll();
	}
	
	public List<ProjectStageCount> projectStageCount(){
		return proRepo.projectStageCount();
	}

	public Project findById(Long id) {
		return proRepo.findById(id).get();
	}

	public void deleteById(Long id) {
		proRepo.deleteById(id);
		
	}

	public Project findByProjectId(long projId) {
		
		return proRepo.findByProjectId(projId);
	}

	public void delete(Project pro) {
		proRepo.delete(pro);
		
	}

}
