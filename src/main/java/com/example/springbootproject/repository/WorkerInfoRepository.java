package com.example.springbootproject.repository;

import com.example.springbootproject.entity.WorkerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerInfoRepository extends JpaRepository<WorkerInfo, Integer> {

}
