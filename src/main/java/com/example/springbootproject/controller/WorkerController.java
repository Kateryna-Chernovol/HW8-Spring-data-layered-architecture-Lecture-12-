package com.example.springbootproject.controller;

import com.example.springbootproject.dto.WorkerDto;
import com.example.springbootproject.entity.Worker;
import com.example.springbootproject.repository.WorkerRepository;
import com.example.springbootproject.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class WorkerController {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private WorkerService workerService;

    @PostMapping("/workers")
    public ResponseEntity<Worker> addWorker(@RequestBody Worker worker) {
        return ResponseEntity.ok(workerRepository.save(worker));
    }

    /**
     * getById (getOne)
     */
    @GetMapping("/workers/{id}")
    public ResponseEntity<WorkerDto> getWorkerById(@PathVariable Integer id) {
        System.out.println(workerService.findById(id));
        WorkerDto returnValue = workerService.findById(id);
        if (Objects.nonNull(returnValue)) {
            return ResponseEntity.ok(workerService.findById(id));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/workers/get-all")
    public List<WorkerDto> getAllWorkers() {
        return workerService.getAllWorkers();
    }

    @PutMapping("/workers/update")
    public ResponseEntity<Worker> updateWorker(@RequestBody Worker worker) {
        Worker returnValue = workerService.updateWorker(worker);
        Integer workerId = worker.getId();

        if (Objects.isNull(returnValue)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(returnValue);
        }
    }

    @PostMapping("/workers/create")
    public ResponseEntity<Worker> createWorker(@RequestBody Worker worker) {
        Worker returnValue = workerService.createWorker(worker);
        if (Objects.isNull(returnValue)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(returnValue);
        }
    }

    @GetMapping("/workers/name/{name}")
    public ResponseEntity<WorkerDto> GetWorkerByName(@PathVariable String name) {
        WorkerDto returnValue = workerService.getByName(name);
        if (Objects.nonNull(returnValue)) {
            return ResponseEntity.ok(returnValue);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/workers/departure/{id}")
    public List<Worker> getWorkersByDepartureId(@PathVariable int id) {
        return workerService.getWorkerByDepartureId(id);
    }
}
