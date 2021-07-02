package com.egen.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long empId;

    @Column(name = "empName")
    private String empName;

    @Column(name = "empEmail")
    private String empEmail;

    public Employee() {
    }

    public Employee(String empName, String empEmail) {
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }
}
