package com.egen.mapper;

import com.egen.model.dto.EmployeeDto;
import com.egen.model.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeMapper {

    ModelMapper modelMapper = new ModelMapper();

    public Employee mapToEntity(EmployeeDto employeeDto) {
        return modelMapper.map(employeeDto, Employee.class);
    }

    public EmployeeDto mapToDto(Optional<Employee> employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }
}
