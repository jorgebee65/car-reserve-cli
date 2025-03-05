package com.amigos.code.model;

public class Booking {
    private String id;
    private Car car;
    private User user;

    public Booking(String id, Car car, User user) {
        this.id = id;
        this.car = car;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", car=" + car +
                ", user=" + user +
                '}';
    }
}
