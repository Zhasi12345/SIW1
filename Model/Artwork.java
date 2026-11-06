package Model;
import java.util.Objects;

public abstract class Artwork implements Comparable<Artwork> {
    private int id;
    private String title;
    private String artist;
    private double price;

    public Artwork(int id, String title, String artist, double price) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.price = price;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getArtist() {
        return artist;
    }
    public double getPrice() {
        return price;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public abstract String getType();

    @Override
    public int compareTo(Artwork other) {
        return Integer.compare(this.id, other.id);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Artwork artwork = (Artwork) obj;
        return id == artwork.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), id);
    }

    @Override
    public String toString() {
        return "[" + getType() + "] ID: " + id + " | Title: " + title + " | Artist: " + artist + " | Price: $" + price;
    }
}
