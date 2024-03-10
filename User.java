import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean authenticate(String enteredUsername, String enteredPassword) {
        return username.equals(enteredUsername) && password.equals(enteredPassword);
    }
}

class ReservationSystem {
    private static Map<String, String> trainDetails = new HashMap<>();
    private static Map<String, String> reservations = new HashMap<>();
    private static int pnrCounter = 1;

    static {
        // Initialize some train details
        trainDetails.put("101", "Express One");
        trainDetails.put("102", "Swift Traveler");
        // Add more train details as needed
    }

    public static void makeReservation(User user, String trainNumber, String classType, String date, String source, String destination) {
        String pnr = "PNR" + pnrCounter++;
        String trainName = trainDetails.get(trainNumber);

        String reservationInfo = String.format("Train: %s (%s)\nClass: %s\nDate: %s\nFrom: %s\nTo: %s",
                trainName, trainNumber, classType, date, source, destination);

        reservations.put(pnr, reservationInfo);
        System.out.println("Reservation Successful!\nPNR: " + pnr);
    }
}

class CancellationSystem {
    public static void cancelReservation(String pnr) {
        if (ReservationSystem.reservations.containsKey(pnr)) {
            System.out.println("Reservation found:");
            System.out.println(ReservationSystem.reservations.get(pnr));

            // Ask for confirmation
            System.out.println("Confirm cancellation? (Type 'OK' to confirm)");
            Scanner scanner = new Scanner(System.in);
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("OK")) {
                ReservationSystem.reservations.remove(pnr);
                System.out.println("Cancellation Successful!");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("Reservation not found for PNR: " + pnr);
        }
    }
}

public class OnlineReservationSystem {
    public static void main(String[] args) {
        // Sample user
        User user = new User("user123", "password");

        // Sample reservation
        ReservationSystem.makeReservation(user, "101", "AC", "2024-03-15", "City A", "City B");

        // Sample cancellation
        CancellationSystem.cancelReservation("PNR1");
    }
}
