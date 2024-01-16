-- INSERT EMPLOYEES			
insert into employee (employee_id, first_name, last_name, gender, email) values (nextval('employee_seq'), 'John', 'Warton', 'Male', 'warton@gmail.com');
insert into employee (employee_id, first_name, last_name, gender, email) values (nextval('employee_seq'), 'Mike', 'Lanister', 'Male', 'lanister@gmail.com');
insert into employee (employee_id, first_name, last_name, gender, email) values (nextval('employee_seq'), 'Steve', 'Reeves', 'Male', 'Reeves@gmail.com');
insert into employee (employee_id, first_name, last_name, gender, email) values (nextval('employee_seq'), 'Ronald', 'Connor', 'Male', 'connor@gmail.com');
insert into employee (employee_id, first_name, last_name, gender, email) values (nextval('employee_seq'), 'Jim', 'Salvator', 'Male', 'Sal@gmail.com');
insert into employee (employee_id, first_name, last_name, gender, email) values (nextval('employee_seq'), 'Peter', 'Henley', 'Male', 'henley@gmail.com');
insert into employee (employee_id, first_name, last_name, gender, email) values (nextval('employee_seq'), 'Richard', 'Carson', 'Male', 'carson@gmail.com');
insert into employee (employee_id, first_name, last_name, gender, email) values (nextval('employee_seq'), 'Honor', 'Miles', 'Male', 'miles@gmail.com');
insert into employee (employee_id, first_name, last_name, gender, email) values (nextval('employee_seq'), 'Tony', 'Roggers', 'Male', 'roggers@gmail.com');

-- INSERT PROJECTS			
insert into project (project_id, name, stage, description) values (nextval('project_seq'), 'Large Production Deploy', 'NOTSTARTED', 'This requires all hands on deck for the final deployment of the software into production');
insert into project (project_id, name, stage, description) values (nextval('project_seq'), 'New Employee Budget',  'COMPLETED', 'Decide on a new employee bonus budget for the year and figureout who will be promoted');
insert into project (project_id, name, stage, description) values (nextval('project_seq'), 'Office Reconstruction', 'INPROGRESS', 'The office building in Monroe has been damaged due to hurricane in the region. This needs to be reconstructed');
insert into project (project_id, name, stage, description) values (nextval('project_seq'), 'Improve Intranet Security', 'INPROGRESS', 'With the recent data hack, the office security needs to be improved and proper security team needs to be hired for implementation');

-- INSERT PROJECT_EMPLOYEE_RELATION
insert into project_employee (employee_id, project_id) (select e.employee_id, p.project_id from employee e, project p where e.last_name ='Warton' AND p.name = 'Large Production Deploy');
insert into project_employee (employee_id, project_id) (select e.employee_id, p.project_id from employee e, project p where e.last_name ='Warton' AND p.name = 'New Employee Budget');
insert into project_employee (employee_id, project_id) (select e.employee_id, p.project_id from employee e, project p where e.last_name ='Warton' AND p.name = 'Office Reconstruction');
insert into project_employee (employee_id, project_id) (select e.employee_id, p.project_id from employee e, project p where e.last_name ='Reeves' AND p.name = 'Large Production Deploy');
insert into project_employee (employee_id, project_id) (select e.employee_id, p.project_id from employee e, project p where e.last_name ='Warton' AND p.name = 'New Employee Budget');
insert into project_employee (employee_id, project_id) (select e.employee_id, p.project_id from employee e, project p where e.last_name ='Warton' AND p.name = 'Improve Intranet Security');
insert into project_employee (employee_id, project_id) (select e.employee_id, p.project_id from employee e, project p where e.last_name ='Henley' AND p.name = 'Office Reconstruction');
insert into project_employee (employee_id, project_id) (select e.employee_id, p.project_id from employee e, project p where e.last_name ='Henley' AND p.name = 'Improve Intranet Security');															