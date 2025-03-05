package com.amigos.code.service;

import com.amigos.code.model.Booking;
import com.amigos.code.model.Car;
import com.amigos.code.model.User;
import com.amigos.code.repository.BookingRepository;
import com.amigos.code.repository.CarRepository;

import java.util.List;
import java.util.stream.Collectors;

public class BookingService {
    private final BookingRepository bookingRepository = new BookingRepository();
    private final CarRepository carRepository = new CarRepository();

    public List<Car> getBookedCars(){
        return bookingRepository.getBookings().stream().map(Booking::getCar).toList();
    }

    public void book(Booking booking){
        bookingRepository.add(booking);
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
