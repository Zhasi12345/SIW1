package Model;

public class DigitalArt extends Artwork {
    private String fileFormat;

    public DigitalArt(int id, String title, String artist, double price, String fileFormat) {
        super(id, title, artist, price);
        this.fileFormat = fileFormat;
    }

    @Override
    public String getType() {
        return "Digital";
    }

    @Override
    public String toString() {
        return super.toString() + " | Format: " + fileFormat;
    }
}
