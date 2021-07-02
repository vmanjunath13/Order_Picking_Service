package com.egen.repository;

import com.egen.model.entity.OrderPickUp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPickUpRepository extends JpaRepository<OrderPickUp, String> {

    Page<OrderPickUp> findAll(Pageable pageable);

//    String findAllByEmployee_EmpIdAndPickUpOrderEndTime(long empId, Timestamp startTime, Timestamp endTime);

}
