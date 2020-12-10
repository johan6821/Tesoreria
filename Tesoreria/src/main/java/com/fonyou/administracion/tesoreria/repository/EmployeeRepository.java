package com.fonyou.administracion.tesoreria.repository;

import com.fonyou.administracion.tesoreria.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository  extends CrudRepository<Employee, Integer> {

}
