package com.amigos.code.repository;

import com.amigos.code.model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarRepository {
    private static final List<Car> cars;

    static {
        cars = new ArrayList<>();
        cars.add(new Car(1, false, "Suzuki", "Swift"));
        cars.add(new Car(2, false, "Jeep", "Compass"));
        cars.add(new Car(3, false, "Nissan", "Versa"));
        cars.add(new Car(4, true, "Tesla", "Model X"));
        cars.add(new Car(5, true, "Tesla", "Model 3"));
    }

    public List<Car> getCars() {
        return cars;
    }

    public Car getCarById(int id) {
        return cars.stream().filter(car -> car.getId() == id ).findFirst().orElse(null);
    }
}
