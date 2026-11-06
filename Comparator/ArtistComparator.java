package Comparator;
import Model.Artwork;
import java.util.Comparator;

public class ArtistComparator implements Comparator<Artwork> {
    @Override
    public int compare(Artwork a1, Artwork a2) {
        return a1.getArtist().compareToIgnoreCase(a2.getArtist());
    }
}