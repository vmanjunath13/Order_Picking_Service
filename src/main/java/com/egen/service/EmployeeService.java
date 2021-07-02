package com.egen.service;

import com.egen.model.dto.EmployeeDto;

public interface EmployeeService {

    Boolean createEmployee(EmployeeDto employeeDto);

    Boolean findEmployeeById(long id);
}
