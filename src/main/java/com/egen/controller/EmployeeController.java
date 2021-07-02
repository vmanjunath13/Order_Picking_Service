package com.egen.controller;

import com.egen.model.dto.EmployeeDto;
import com.egen.model.dto.OrderPickUpDto;
import com.egen.response.Response;
import com.egen.response.ResponseMetaData;
import com.egen.response.StatusMessage;
import com.egen.service.EmployeeService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/employee")
@Api("Employee related endpoints")
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value="/register",
            consumes = "application/json",
            produces = "application/json")
    public Response<Boolean> createEmployee(@RequestBody EmployeeDto employeeDto){
        return employeeService.createEmployee(employeeDto) == Boolean.TRUE ?
                Response.<Boolean>builder().meta(ResponseMetaData.builder().statusCode(201)
                        .statusMessage(StatusMessage.CREATED.name())
                        .build()).data(true).build()
                :
                Response.<Boolean>builder().meta(ResponseMetaData.builder().statusCode(409)
                        .statusMessage(StatusMessage.CONFLICT.name())
                        .build()).data(false).build();
    }

    @GetMapping(value="/{id}", produces = "application/json")
    @ApiOperation(value = "Fetches a specific employee for given id ",
            notes = "Returns a specific employee")
    @ApiResponses(value={
            @ApiResponse(code=302,message = "FOUND"),
            @ApiResponse(code=500,message = "Interval Server Error"),
            @ApiResponse(code=200,message = "OK")
    })
    public Response<Boolean> getEmployeeById(@ApiParam(value = "Employee Id of employee",required = true)
                                                       @PathVariable("id") Long id) {
        return employeeService.findEmployeeById(id) == Boolean.TRUE ?
                Response.<Boolean>builder().meta(ResponseMetaData.builder().statusCode(201)
                        .statusMessage(StatusMessage.CREATED.name())
                        .build()).data(true).build()
                :
                Response.<Boolean>builder().meta(ResponseMetaData.builder().statusCode(409)
                        .statusMessage(StatusMessage.CONFLICT.name())
                        .build()).data(false).build();
    }

}
