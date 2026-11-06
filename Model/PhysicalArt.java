package Model;

public class PhysicalArt extends Artwork {
    private String material;

    public PhysicalArt(int id, String title, String artist, double price, String material) {
        super(id, title, artist, price);
        this.material = material;
    }

    @Override
    public String getType() {
        return "Physical";
    }

    @Override
    public String toString() {
        return super.toString() + " | Material: " + material;
    }
}
