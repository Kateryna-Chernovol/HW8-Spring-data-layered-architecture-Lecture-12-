package com.example.springbootproject.service;

import com.example.springbootproject.dto.WorkerDto;
import com.example.springbootproject.entity.Departure;
import com.example.springbootproject.entity.Worker;
import com.example.springbootproject.logger.Logger;
import com.example.springbootproject.repository.DepartureRepository;
import com.example.springbootproject.repository.WorkerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WorkerService {

    @Autowired
    private Logger logger;

    @Autowired
    private DepartureRepository departureRepository;

    @Autowired
    private WorkerRepository workerRepository;

    public WorkerDto findById(Integer id) {
        Optional<Worker> returnWorker = workerRepository.findById(id);
        if (returnWorker.isPresent()) {
            Worker worker = returnWorker.get();
            return WorkerDto.builder()
                    .id(worker.getId())
                    .name(worker.getName())
                    .salary(worker.getSalary())
                    .build();
        }
        return null;
    }

    public List<WorkerDto> getAllWorkers() {
        Iterable<Worker> iterable = workerRepository.findAll();

        List<WorkerDto> workers = StreamSupport
                .stream(iterable.spliterator(), false)
                .map(worker -> {
                    WorkerDto dto = new WorkerDto();
                    BeanUtils.copyProperties(worker, dto);
                    return dto;
                }).collect(Collectors.toList());

        return workers;
    }

    public Worker updateWorker(Worker worker) {
        Optional<Worker> workerById = workerRepository.findById(worker.getId());
        if (workerById.isEmpty()) {
            logger.logMessage(String.format("No such worker to update with id = %s", worker.getId()));
            return null;
        } else if (workerById.isPresent()) {
            return workerRepository.save(worker);
        } else {
            return null;
        }
    }

    public Worker createWorker(Worker worker) {
        Optional<Worker> workerById = workerRepository.findById(worker.getId());
        if (workerById.isPresent()) {
            return null;
        } else {
            Worker saveWorker = workerRepository.save(worker);
            return saveWorker;
        }
    }

    public WorkerDto getByName(String name) {
        Optional<Worker> worker = Optional.ofNullable(workerRepository.findByName(name));
        if (worker.isPresent()) {
            Worker workerExist = workerRepository.findByName(name);
            WorkerDto workerDto = new WorkerDto()
                    .setId(workerExist.getId())
                    .setName(workerExist.getName())
                    .setSalary(workerExist.getSalary())
                    .setWorkerInfo(workerExist.getWorkerInfo());
            return workerDto;
        } else {
            return null;
        }
    }

    public List<Worker> getWorkerByDepartureId(@PathVariable int id) {
        Optional<Departure> departureById = departureRepository.findById(id);
        if (departureById.isPresent()) {
            return workerRepository.findAllByDepartureId(id);
        } else {
            return null;
        }
    }
}
