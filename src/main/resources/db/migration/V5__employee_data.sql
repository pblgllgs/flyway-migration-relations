insert into employees (id, birthdate, email, firstname, identifier, lastname, role, address_id, department_id)
values (nextVal('id_employee_sequence_generator'), CURRENT_DATE, 'pbl.gllgs@gmail.com','pbl',123,'gllgs','ROLE_ADMIN',1, 1);