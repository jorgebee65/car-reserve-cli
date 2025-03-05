package com.amigos.code.repository;

import com.amigos.code.model.Booking;

import java.util.ArrayList;
import java.util.List;

public class BookingRepository {
    private static final List<Booking> bookings;

    static {
        bookings = new ArrayList<>();
    }

    public void add(Booking booking) {
        bookings.add(booking);
    }

    public List<Booking> getBookings() {
        return bookings;
    }
}
