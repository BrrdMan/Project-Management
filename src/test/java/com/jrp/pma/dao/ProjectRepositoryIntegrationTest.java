package com.jrp.pma.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;

import com.jrp.pma.entities.Project;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@ContextConfiguration(classes=ProjectManagementApplication.class)
@SpringBootTest
@SqlGroup({@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql", "classpath:data.sql"}),
        @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:drop.sql")})
class ProjectRepositoryIntegrationTest {
	
	@Autowired
	ProjectRepository proRepo;

    @Test
    void ifNewProjectSaved_thenSuccess() {
		Project newProject = new Project("Test", "COMPLETE", "Test Description");
		proRepo.save(newProject);
		
		assertEquals(5, proRepo.findAll().size());
	}
	
	

}
