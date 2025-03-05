package com.amigos.code;

import com.amigos.code.model.Booking;
import com.amigos.code.model.Car;
import com.amigos.code.model.User;
import com.amigos.code.service.BookingService;
import com.amigos.code.service.UserService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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

                // Validar entrada del usuario
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number between 1 and 7.");
                    scanner.next(); // Descartar entrada inválida
                }
                option = scanner.nextInt();

                // Manejo de opciones
                switch (option) {
                    case 1:
                        System.out.println("🚗 You selected: Book Car.");
                        bookCar(scanner, bookingService, userService);
                        break;
                    case 2:
                        System.out.println("📋 You selected: View All User Booked Cars.");
                        getUsersWhoBookedCars().forEach(System.out::println);
                        break;
                    case 3:
                        System.out.println("📜 You selected: View All Bookings.");
                        getBookings().forEach(System.out::println);
                        break;
                    case 4:
                        System.out.println("🚘 You selected: View Available Cars.");
                        getAvailableCars().forEach(System.out::println);
                        break;
                    case 5:
                        System.out.println("🔋 You selected: View Available Electric Cars.");
                        getAvailableElectricCars().forEach(System.out::println);
                        break;
                    case 6:
                        System.out.println("👥 You selected: View All Users.");
                        getUsers().forEach(user -> System.out.println(user.toString()));
                        break;
                    case 7:
                        System.out.println("👋 Exiting the program. Goodbye!");
                        break;
                    default:
                        System.out.println("❌ Invalid option. Please enter a number between 1 and 7.");
                }
            } while (option != 7); // Repetir hasta que el usuario elija salir

            scanner.close();

    }

    private static List<User> getUsers() {
        return userService.getUsers();
    }

    private static List<Car> getAvailableCars() {
        return bookingService.getAvailableRegularCars();
    }

    private static List<Car> getAvailableElectricCars() {
        return bookingService.getAvailableElectricCars();
    }

    private static List<Booking> getBookings() {
        return bookingService.getAllBookings();
    }

    private static List<User> getUsersWhoBookedCars() {
        return bookingService.getAllUsersWhoBooked();
    }

    private static void bookCar(Scanner scanner, BookingService bookingService, UserService userService){
        String userId;
        System.out.println("Insert the ID of the user you would like to book");
        userId = scanner.next();
        int carId;
        System.out.println("Insert the ID of the car you would like to book");
        carId = scanner.nextInt();
        var user = userService.getUserbyId(userId);
        var car = bookingService.getCarById(carId);
        UUID uuid = UUID.randomUUID();
        bookingService.book(new Booking(uuid.toString(), car, user));
        System.out.println("Car Booked!");
    }
}