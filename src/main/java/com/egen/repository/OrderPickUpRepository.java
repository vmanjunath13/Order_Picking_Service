package com.egen.repository;

import com.egen.model.entity.OrderPickUp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OrderPickUpRepository extends JpaRepository<OrderPickUp, String> {

    Page<OrderPickUp> findAll(Pageable pageable);

    List<OrderPickUp> findAllByEmployee_EmpIdAndPickUpOrderEndTime(long empId, Timestamp startTime, Timestamp endTime);

}
