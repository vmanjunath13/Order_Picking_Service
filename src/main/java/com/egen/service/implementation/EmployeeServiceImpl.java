package com.egen.service.implementation;

import com.egen.mapper.EmployeeMapper;
import com.egen.model.dto.EmployeeDto;
import com.egen.model.entity.Employee;
import com.egen.repository.EmployeeRepository;
import com.egen.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public Boolean createEmployee(EmployeeDto employeeDto) {
        employeeRepository.save(new Employee(employeeDto.getEmpName(), employeeDto.getEmpEmail()));
        return true;
    }

    @Override
    public Boolean findEmployeeById(long id) {
        Employee employee = employeeRepository.getById(id);
        return employee != null;
    }
}
