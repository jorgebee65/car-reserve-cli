package com.amigos.code.service;

import com.amigos.code.model.Booking;
import com.amigos.code.model.Car;
import com.amigos.code.model.User;
import com.amigos.code.repository.BookingRepository;
import com.amigos.code.repository.CarRepository;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookingService {
    private final BookingRepository bookingRepository = new BookingRepository();
    private final CarRepository carRepository = new CarRepository();

    public void book( UserService userService, String userId, int carId){
        try {
            var user = userService.getUserbyId(userId);
            if (user == null) {
                throw new IllegalStateException("Error: User not found");
            }
            var car = carRepository.getCarById(carId);
            if (car == null) {
                throw new IllegalStateException("Error: Car not found");
            }
            bookingRepository.getBookings()
                    .stream()
                    .filter(b -> b.getCar().getId() == car.getId())
                    .findFirst()
                    .ifPresent(b -> {
                        throw new IllegalStateException("Error: Car already booked");
                    });

            UUID uuid = UUID.randomUUID();
            var booking = new Booking(uuid.toString(), car, user);
            bookingRepository.add(booking);
            String confirmationMessage = """
                        🎉 Successfully booked car with reg number %s for user %s
                        Booking ref: %s
                        """.formatted(car.getId(), user, uuid);
            System.out.println(confirmationMessage);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<Car> getAvailableCars(boolean electric) {
        var allCars = carRepository.getCars().stream()
                .filter(car -> car.isElectric() == electric)
                .toList();

        var bookedCars = bookingRepository.getBookings().stream()
                .map(Booking::getCar)
                .filter(car -> car.isElectric() == electric)
                .toList();

        return allCars.stream()
                .filter(car -> !bookedCars.contains(car))
                .toList();
    }

    public List<Car> getAvailableRegularCars() {
        return getAvailableCars(false);
    }

    public List<Car> getAvailableElectricCars() {
        return getAvailableCars(true);
    }

    public List<Booking> getAllBookings(){
        return bookingRepository.getBookings();
    }

    public Car getCarById(int id){
        return carRepository.getCarById(id);
    }

    public List<User> getAllUsersWhoBooked() {
        return bookingRepository.getBookings().stream().map(Booking::getUser).collect(Collectors.toList());
    }
}
