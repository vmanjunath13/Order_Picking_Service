package com.egen.model.entity;

import com.egen.model.enums.OrderPickingMethod;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "EmployeePerformance")
public class EmployeePerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long empPerfId;

    @Column(name = "perfStartDate")
    private Timestamp perfStartDate;

    @Column(name = "perfEndDate")
    private Timestamp perfEndDate;

    public EmployeePerformance() {
    }


    public Timestamp getPerfStartDate() {
        return perfStartDate;
    }

    public void setPerfStartDate(Timestamp perfStartDate) {
        this.perfStartDate = perfStartDate;
    }

    public Timestamp getPerfEndDate() {
        return perfEndDate;
    }

    public void setPerfEndDate(Timestamp perfEndDate) {
        this.perfEndDate = perfEndDate;
    }

    public long getEmpPerfId() {
        return empPerfId;
    }

    public void setEmpPerfId(long empPerfId) {
        this.empPerfId = empPerfId;
    }
}
