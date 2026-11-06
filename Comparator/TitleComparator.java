package Comparator;
import Model.Artwork;
import java.util.Comparator;

public class TitleComparator implements Comparator<Artwork> {
    @Override
    public int compare(Artwork a1, Artwork a2) {
        return a1.getTitle().compareToIgnoreCase(a2.getTitle());
    }
}