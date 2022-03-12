package com.example.springbootproject.repository;

import com.example.springbootproject.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {

    @Query("select w from Worker as w where w.departure.id = :departureId")
    List<Worker> findAllByDepartureId(@Param("departureId") int departureId);

    @Query("select w from Worker as w where w.name = :name")
    Worker findByName(String name);

}
