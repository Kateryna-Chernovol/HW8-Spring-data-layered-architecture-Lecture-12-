package com.example.springbootproject.dto;

import com.example.springbootproject.entity.WorkerInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WorkerDto {
    private int id;
    private String name;
    private double salary;
    private WorkerInfo workerInfo;
}
