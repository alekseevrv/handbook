package com.example.handbook.repository;

import com.example.handbook.entity.Insurance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InsuranceRepository extends CrudRepository<Insurance, Long> {

    @Query("select i from Insurance i order by i.id asc")
    List<Insurance> findAllOrderById();

    @Query("select i from Insurance i where i.inn = ?1 or i.ogrn = ?2 or i.name = ?3")
    List<Insurance> findAllByInnOrOgrnOrName(String inn, String ogrn, String name);

}
