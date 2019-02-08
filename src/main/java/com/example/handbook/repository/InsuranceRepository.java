package com.example.handbook.repository;

import com.example.handbook.entity.Insurance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InsuranceRepository extends CrudRepository<Insurance, Long> {

    @Query("select i from Insurance i order by i.id asc")
    List<Insurance> findAllOrderById();

}
