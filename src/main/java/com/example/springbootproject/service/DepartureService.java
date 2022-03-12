package com.example.springbootproject.service;

import com.example.springbootproject.dto.DepartureDto;
import com.example.springbootproject.entity.Departure;
import com.example.springbootproject.entity.Worker;
import com.example.springbootproject.logger.Logger;
import com.example.springbootproject.repository.DepartureRepository;
import com.example.springbootproject.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class DepartureService {

    @Autowired
    Logger logger;

    @Autowired
    DepartureRepository departureRepository;

    @Autowired
    WorkerRepository workerRepository;

    public String getMessageWithRandomNUmber() {
        String message = "message with " + new Random().nextInt(10);
        logger.logMessage(message);
        return message;
    }

    public DepartureDto getByName(String name) {

        Departure departureByName = departureRepository.findDepartureByName(name);

        if (Objects.isNull(departureByName)) {
            return null;
        }

        List<Worker> workers = workerRepository.findAllByDepartureId(departureByName.getId());

        DepartureDto departureDto = new DepartureDto()
                .setName(departureByName.getName())
                .setId(departureByName.getId())
                .setLocation(departureByName.getLocation())
                .setWorkerNames(workers.stream().map(Worker::getName).collect(Collectors.toList()));

        return departureDto;
    }

    public Departure addDeparture(Departure departure) {
        Optional<Departure> departureById = departureRepository.findById(departure.getId());

        if (departureById.isEmpty()) {
            return departureRepository.save(departure);
        } else {
            logger.logMessage(String.format("Departure with id = %s already exists", departure.getId()));
            return null;
        }
    }

    public Departure updateDeparture(Departure departure) {
        Optional<Departure> departureById = departureRepository.findById(departure.getId());

        if (departureById.isEmpty()) {
            logger.logMessage(String.format("No such departure to update with id = %s", departure.getId()));
            return null;
        } else {
            return departureRepository.save(departure);
        }
    }

    public List<Departure> getDepartures() {

        return departureRepository.findAll();
    }
}
