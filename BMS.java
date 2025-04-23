import java.util.*;

public class BMS {

    enum BookingStatus {
        PENDING,
        CONFIRMED,
        CANCELLED
    }

    enum SeatType {
        REGULAR,
        PREMIUM
    }

    enum PaymentStatus {
        SUCCESS,
        FAILED,
        PENDING
    }

    class Address {
        String street, city, state, zip;

        Address(String street, String city, String state, String zip) {
            this.street = street;
            this.city = city;
            this.state = state;
            this.zip = zip;
        }
    }

    class Account {
        String id, password;

        Account(String id, String password) {
            this.id = id;
            this.password = password;
        }
    }

    class User {
        String name;
        String email;
        Account account;
        Address address;

        User(String name, String email, Account account, Address address) {
            this.name = name;
            this.email = email;
            this.account = account;
            this.address = address;
        }
    }

    class Payment {
        String transactionId;
        double amount;
        Date paymentDate;
        PaymentStatus status;

        Payment(double amount, PaymentStatus status) {
            this.transactionId = UUID.randomUUID().toString();
            this.amount = amount;
            this.status = status;
            this.paymentDate = new Date();
        }
    }

    class SeatShow {
        int seatId;
        boolean reserved;
        SeatType type;
        double price;

        SeatShow(int seatId, boolean reserved, SeatType type, double price) {
            this.seatId = seatId;
            this.reserved = reserved;
            this.type = type;
            this.price = price;
        }
    }

    class Show {
        String id;
        List<SeatShow> seats;

        Show(String id) {
            this.id = id;
            this.seats = new ArrayList<>();
        }
    }

    class Movie {
        String title;
        List<Show> shows;

        Movie(String title) {
            this.title = title;
            this.shows = new ArrayList<>();
        }

        void addShow(Show show) {
            this.shows.add(show);
        }
    }

    class Booking {
        UUID uuid = UUID.randomUUID();
        BookingStatus status;
        List<SeatShow> seats;
        Show show;
        User user;
        Payment payment;

        Booking(BookingStatus status, List<SeatShow> seats, Show show, User user, Payment payment) {
            this.status = status;
            this.seats = seats;
            this.show = show;
            this.user = user;
            this.payment = payment;
        }
    }

    public class MovieBookingSystem {
        public void run() {
            // Create movie
            Movie movie = new Movie("Inception");

            // Create shows
            Show show1 = new Show("Show1");
            show1.seats.add(new SeatShow(1, false, SeatType.REGULAR, 10.0));
            show1.seats.add(new SeatShow(2, false, SeatType.PREMIUM, 20.0));

            Show show2 = new Show("Show2");
            show2.seats.add(new SeatShow(3, false, SeatType.REGULAR, 10.0));
            show2.seats.add(new SeatShow(4, false, SeatType.PREMIUM, 20.0));

            // Add shows to movie
            movie.addShow(show1);
            movie.addShow(show2);

            // Create a user
            Account account = new Account("user123", "password123");
            Address address = new Address("MG Road", "Bangalore", "Karnataka", "560001");
            User user = new User("Chinmay", "chinmay@example.com", account, address);

            // Create payment
            Payment payment = new Payment(20.0, PaymentStatus.SUCCESS);

            // Book a seat
            List<SeatShow> bookedSeats = new ArrayList<>();
            bookedSeats.add(show1.seats.get(1)); // Booking seat 2 (PREMIUM)

            // Mark seat as reserved
            show1.seats.get(1).reserved = true;

            // Create booking
            Booking booking = new Booking(BookingStatus.CONFIRMED, bookedSeats, show1, user, payment);

            // Print booking details
            System.out.println("✅ Booking Confirmed!");
            System.out.println("Booking ID: " + booking.uuid);
            System.out.println("User: " + booking.user.name + " (" + booking.user.email + ")");
            System.out.println("Movie: " + movie.title);
            System.out.println("Show: " + show1.id);
            System.out.println("Seats Booked: ");
            for (SeatShow seat : booking.seats) {
                System.out.println("Seat ID: " + seat.seatId + ", Type: " + seat.type + ", Price: $" + seat.price);
            }
            System.out.println("Payment ID: " + booking.payment.transactionId);
            System.out.println("Amount Paid: $" + booking.payment.amount);
            System.out.println("Payment Status: " + booking.payment.status);
            System.out.println("Booking Status: " + booking.status);
        }
    }

    // Main method to run
    public static void main(String[] args) {
        BMS system = new BMS();
        MovieBookingSystem bookingSystem = system.new MovieBookingSystem();
        bookingSystem.run();
    }
}
