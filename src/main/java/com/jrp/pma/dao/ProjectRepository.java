package com.jrp.pma.dao;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jrp.pma.dto.ProjectStageCount;
import com.jrp.pma.entities.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>, PagingAndSortingRepository<Project, Long>  {
	
	@Override
	public List<Project> findAll();
	
	@Query(nativeQuery=true, value=" SELECT stage as label, COUNT(*) as projectStageCount "+
								 "FROM project "+
								 "GROUP BY stage")
           
	List<ProjectStageCount> projectStageCount();
	
	public Project findByProjectId(long projId); 
}
