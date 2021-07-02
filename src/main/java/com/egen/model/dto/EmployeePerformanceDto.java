package com.egen.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeePerformanceDto implements Serializable {

    private long empPerfId;
    @JsonProperty(value = "perfStartDate")
    private Timestamp perfStartDate;
    @JsonProperty(value = "perfEndDate")
    private Timestamp perfEndDate;

    public EmployeePerformanceDto() {
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
