import Model.*;
import Service.ArtworkManager;
import Comparator.*;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ArtworkManager manager = new ArtworkManager();
    private static Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        User admin = new User("admin", "Admin", "admin123");
        users.put("admin", admin);

        manager.addArtwork(new DigitalArt(1, "Neon Sunset", "Alice", 150.0, "PNG"));
        manager.addArtwork(new PhysicalArt(2, "Marble Statue", "Bob", 500.0, "Marble"));
        manager.addArtwork(new DigitalArt(3, "Cyber City", "Alice", 200.0, "PSD"));

        System.out.println("   ");
        System.out.println("  ' ARTWORK MANAGEMENT SYSTEM ' ");
        System.out.println("  ");

        mainMenu();
    }

    private static void mainMenu() {
        while (true) {
            System.out.println("\n MAIN MENU: ");
            System.out.println("1. Sign in");
            System.out.println("2. Register as User");
            System.out.println("0. Exit");
            System.out.println("   ");

            int choice = readInt("Choose an option: ");
            switch (choice) {
                case 1:
                    signIn();
                    break;
                case 2:
                    registerUser();
                    break;
                case 0:
                    System.out.println("Thank you for using the system!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void signIn() {
        System.out.println("\n SIGN IN ");
        String name = readText("Username: ");
        String password = readText("Password: ");

        User user = users.get(name.toLowerCase());

        if (user == null || !user.checkPassword(password)) {
            System.out.println("Incorrect username or password.");
            return;
        }

        System.out.println("\nWelcome, " + user.getName() + "!");
        if (user.getRole().equalsIgnoreCase("Admin")) {
            adminMenu(user);
        } else {
            userMenu(user);
        }
    }

    private static void registerUser() {
        System.out.println("\n REGISTRATION: ");
        String name = readText("Enter username: ");
        if (users.containsKey(name.toLowerCase())) {
            System.out.println("This username is already taken.");
            return;
        }

        String password = readText("Enter password: ");
        String confirmPassword = readText("Confirm password: ");

        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match.");
            return;
        }

        User user = new User(name, "User", password);
        users.put(name.toLowerCase(), user);
        System.out.println("Registration successful!");
    }

    private static void userMenu(User user) {
        while (true) {
            System.out.println("\n USER MENU  ");
            System.out.println("1. View all artworks");
            System.out.println("2. Sort artworks");
            System.out.println("3. Search artwork by ID");
            System.out.println("4. Search artworks by category");
            System.out.println("5. Submit artwork request");
            System.out.println("0. Log out");
            System.out.println("  ");

            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    showAllArtworks();
                    break;
                case 2:
                    sortMenu();
                    break;
                case 3:
                    searchById();
                    break;
                case 4:
                    searchByCategory();
                    break;
                case 5:
                    submitArtworkRequest(user);
                    break;
                case 0:
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
    private static void adminMenu(User admin) {
        while (true) {
            System.out.println("\n  ADMIN MENU  ");
            System.out.println("1. View all artworks");
            System.out.println("2. Add artwork");
            System.out.println("3. Update artwork");
            System.out.println("4. Remove artwork");
            System.out.println("5. View pending requests");
            System.out.println("6. Process next request");
            System.out.println("7. View analytics");
            System.out.println("0. Log out");
            System.out.println("  ");

            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    showAllArtworks();
                    break;
                case 2:
                    addArtwork();
                    break;
                case 3:
                    updateArtwork();
                    break;
                case 4:
                    removeArtwork();
                    break;
                case 5:
                    showPendingRequests();
                    break;
                case 6:
                    processRequest(admin);
                    break;
                case 7:
                    manager.printAnalytics();
                    break;
                case 0:
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void showAllArtworks() {
        System.out.println("\n  ALL ARTWORKS: ");
        List<Artwork> artworks = manager.getAllSortedNaturally();
        if (artworks.isEmpty()) {
            System.out.println("No artworks available.");
            return;
        }

        for (Artwork artwork : artworks) {
            System.out.println(artwork);
        }
    }

    private static void sortMenu() {
        System.out.println("\n  SORT ARTWORKS:  ");
        System.out.println("1. Sort by ID");
        System.out.println("2. Sort by title");
        System.out.println("3. Sort by price");
        System.out.println("4. Sort by artist");

        int choice = readInt("Choose sorting method: ");
        List<Artwork> artworks;

        switch (choice) {
            case 1:
                artworks = manager.getAllSortedNaturally();
                break;
            case 2:
                artworks = manager.getAllSorted(new TitleComparator());
                break;
            case 3:
                artworks = manager.getAllSorted(new PriceComparator());
                break;
            case 4:
                artworks = manager.getAllSorted(new ArtistComparator());
                break;
            default:
                System.out.println("Invalid option.");
                return;
        }

        for (Artwork artwork : artworks) {
            System.out.println(artwork);
        }
    }

    private static void searchById() {
        int id = readInt("Enter artwork ID: ");

        Artwork artwork = manager.getById(id);

        if (artwork == null) {
            System.out.println("Artwork not found.");
        } else {
            System.out.println(artwork);
        }
    }

    private static void searchByCategory() {
        System.out.println("Available categories: Digital, Physical");

        String category = readText("Enter category: ");
        List<Artwork> artworks = manager.getByCategory(category);
        if (artworks.isEmpty()) {
            System.out.println("No artworks found in this category.");
            return;
        }

        for (Artwork artwork : artworks) {
            System.out.println(artwork);
        }
    }

    private static void submitArtworkRequest(User user) {
        int id = readInt("Enter artwork ID to request: ");
        Artwork artwork = manager.getById(id);

        if (artwork == null) {
            System.out.println("Artwork not found.");
            return;
        }
        user.submitRequest(manager, artwork);
    }
    private static void addArtwork() {
        System.out.println("\n  ADD ARTWORK:  ");
        int id = readInt("Enter artwork ID: ");
        String title = readText("Enter title: ");
        String artist = readText("Enter artist name: ");

        double price = readDouble("Enter price: ");
        if (price < 0) {
            System.out.println("Price cannot be negative.");
            return;
        }
        System.out.println("1. Digital artwork");
        System.out.println("2. Physical artwork");

        int type = readInt("Choose artwork type: ");

        Artwork artwork;
        try {
            if (type == 1) {
                String format = readText("Enter file format: ");
                artwork = new DigitalArt(id, title, artist, price, format);
            } else if (type == 2) {
                String material = readText("Enter material: ");
                artwork = new PhysicalArt(id, title, artist, price, material);
            } else {
                System.out.println("Invalid artwork type.");
                return;
            }

            if (manager.addArtwork(artwork)) {
                System.out.println("Artwork added successfully!");
            } else {
                System.out.println("Artwork ID already exists.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateArtwork() {
        System.out.println("\n  UPDATE ARTWORK:  ");

        int id = readInt("Enter artwork ID to update: ");
        Artwork oldArtwork = manager.getById(id);

        if (oldArtwork == null) {
            System.out.println("Artwork not found.");
            return;
        }

        System.out.println("Current information:");
        System.out.println(oldArtwork);

        String title = readText("Enter new title: ");
        String artist = readText("Enter new artist: ");
        double price = readDouble("Enter new price: ");

        if (price < 0) {
            System.out.println("Price cannot be negative.");
            return;
        }
        Artwork updatedArtwork;
        try {
            if (oldArtwork instanceof DigitalArt) {
                String format = readText("Enter new file format: ");
                updatedArtwork = new DigitalArt(
                        id, title, artist, price, format
                );
            } else {
                String material = readText("Enter new material: ");

                updatedArtwork = new PhysicalArt(
                        id, title, artist, price, material
                );
            }
            if (manager.updateArtwork(updatedArtwork)) {
                System.out.println("Artwork updated successfully!");
            } else {
                System.out.println("Could not update artwork.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void removeArtwork() {
        System.out.println("  REMOVE ARTWORK: ");
        int id = readInt("Enter artwork ID to remove: ");

        if (manager.removeArtwork(id)) {
            System.out.println("Artwork removed successfully!");
        } else {
            System.out.println("Artwork not found.");
        }
    }

    private static void showPendingRequests() {
        System.out.println("  PENDING REQUESTS:  ");
        List<Request> requests = manager.getPendingRequests();
        if (requests.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }

        for (Request request : requests) {
            System.out.println(request);
        }
    }

    private static void processRequest(User admin) {
        List<Request> requests = manager.getPendingRequests();
        if (requests.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }

        System.out.println("\nNext request:");
        System.out.println(requests.get(0));

        System.out.println("\n1. Approve");
        System.out.println("2. Reject");
        System.out.println("0. Cancel");

        int choice = readInt("Choose an option: ");
        if (choice == 1) {
            admin.processRequest(manager, true);

        } else if (choice == 2) {
            admin.processRequest(manager, false);

        } else if (choice == 0) {
            System.out.println("Request was not processed.");
        } else {
            System.out.println("Invalid option.");
        }
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readDouble(String message) {

        while (true) {
            System.out.print(message);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readText(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("This field cannot be empty.");
        }
    }
}
