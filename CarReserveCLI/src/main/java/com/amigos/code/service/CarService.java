package com.amigos.code.service;

import com.amigos.code.model.Car;
import com.amigos.code.repository.CarRepository;

import java.util.List;

public class CarService {
    private final CarRepository carRepository = new CarRepository();

    public List<Car> getCars() {
        return carRepository.getCars();
    }
}
