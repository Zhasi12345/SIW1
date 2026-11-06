package Model;
import Service.ArtworkManager;

public class User {
    private String name;
    private String role;
    private String password;

    public User(String name, String role, String password) {
        this.name = name;
        this.role = role;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public boolean checkPassword(String enteredPassword) {
        return password.equals(enteredPassword);
    }

    public void submitRequest(ArtworkManager manager, Artwork artwork) {
        manager.submitRequest(this, artwork);
    }

    public void processRequest(ArtworkManager manager, boolean approve) {
        manager.processNextRequest(this, approve);
    }
}
