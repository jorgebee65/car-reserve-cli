package com.amigos.code;

import com.amigos.code.model.Booking;
import com.amigos.code.service.BookingService;
import com.amigos.code.service.UserService;

import java.util.Scanner;
import java.util.UUID;

public class Main {

    private static UserService userService;
    private static BookingService bookingService;
    public static void main(String[] args) {
            userService = new UserService();
            bookingService = new BookingService();
            Scanner scanner = new Scanner(System.in);
            int option;

            do {
                System.out.println("\n========== Car Reservation CLI ==========");
                System.out.println("1️⃣ - Book Car");
                System.out.println("2️⃣ - View All User Booked Cars");
                System.out.println("3️⃣ - View All Bookings");
                System.out.println("4️⃣ - View Available Cars");
                System.out.println("5️⃣ - View Available Electric Cars");
                System.out.println("6️⃣ - View All Users");
                System.out.println("7️⃣ - Exit");
                System.out.print("Select an option (1-7): ");

                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number between 1 and 7.");
                    scanner.next();
                }
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("🚗 You selected: Book Car.");
                        bookCar(scanner, bookingService, userService);
                        break;
                    case 2:
                        System.out.println("📋 You selected: View All User Booked Cars.");
                        getUsersWhoBookedCars();
                        break;
                    case 3:
                        System.out.println("📜 You selected: View All Bookings.");
                        getBookings();
                        break;
                    case 4:
                        System.out.println("🚘 You selected: View Available Cars.");
                        getAvailableCars(false);
                        break;
                    case 5:
                        System.out.println("🔋 You selected: View Available Electric Cars.");
                        getAvailableCars(true);
                        break;
                    case 6:
                        System.out.println("👥 You selected: View All Users.");
                        getUsers();
                        break;
                    case 7:
                        System.out.println("👋 Exiting the program. Goodbye!");
                        break;
                    default:
                        System.out.println("❌ Invalid option. Please enter a number between 1 and 7.");
                }
            } while (option != 7);

            scanner.close();

    }

    private static void getUsers() {
        var users = userService.getUsers();
        if(users.isEmpty()) {
            System.out.println("❌ No users in the system");
        } else {
            users.forEach(user -> System.out.println(user.toString()));
        }
    }

    private static void getAvailableCars(boolean isElectric) {
        var cars = isElectric ? bookingService.getAvailableElectricCars() : bookingService.getAvailableRegularCars();
        if(cars.isEmpty()) {
            System.out.println("❌ No "+ ( isElectric ? "electric" : "")+" cars available for renting");
        } else {
            cars.forEach(System.out::println);
        }
    }

    private static void getBookings() {
        var bookings = bookingService.getAllBookings();
        if(bookings.isEmpty()) {
            System.out.println("❌ No Bookings in the system");
        } else {
            bookings.forEach(booking -> System.out.println(booking.toString()));
        }
    }

    private static void getUsersWhoBookedCars() {
        var users = bookingService.getAllUsersWhoBooked();
        if(users.isEmpty()) {
            System.out.println("❌ No Users who booked in the system");
        } else {
            users.forEach(user -> System.out.println(user.toString()));
        }
    }

    private static void bookCar(Scanner scanner, BookingService bookingService, UserService userService){
        String userId;
        System.out.println("Insert the ID of the user you would like to book");
        userId = scanner.next();
        int carId;
        System.out.println("Insert the ID of the car you would like to book");
        carId = scanner.nextInt();
        bookingService.book( userService, userId, carId);
    }
}