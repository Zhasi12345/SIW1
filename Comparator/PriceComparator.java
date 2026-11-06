package Comparator;
import Model.Artwork;
import java.util.Comparator;

public class PriceComparator implements Comparator<Artwork> {
    @Override
    public int compare(Artwork a1, Artwork a2) {
        return Double.compare(a1.getPrice(), a2.getPrice());
    }
}