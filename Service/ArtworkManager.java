package Service;
import Model.Artwork;
import Model.User;
import Model.Request;
import java.util.*;

public class ArtworkManager {
    private Set<Artwork> artworkSet = new HashSet<>();
    private Map<Integer, Artwork> idMap = new HashMap<>();
    private Map<String, List<Artwork>> categoryMap = new HashMap<>();
    private Queue<Request> requestQueue = new LinkedList<>();

    public boolean addArtwork(Artwork artwork) {
        if (artwork == null || idMap.containsKey(artwork.getId())) {
            return false;
        }
        artworkSet.add(artwork);
        idMap.put(artwork.getId(), artwork);
        categoryMap.putIfAbsent(artwork.getType(), new ArrayList<>());
        categoryMap.get(artwork.getType()).add(artwork);

        return true;
    }
    public boolean updateArtwork(Artwork updatedArtwork) {
        if (updatedArtwork == null) {
            return false;
        }
        Artwork oldArtwork = idMap.get(updatedArtwork.getId());

        if (oldArtwork == null) {
            return false;
        }

        artworkSet.remove(oldArtwork);
        idMap.remove(oldArtwork.getId());

        List<Artwork> oldCategory = categoryMap.get(oldArtwork.getType());

        if (oldCategory != null) {
            oldCategory.remove(oldArtwork);
        }

        artworkSet.add(updatedArtwork);
        idMap.put(updatedArtwork.getId(), updatedArtwork);

        categoryMap.putIfAbsent(updatedArtwork.getType(), new ArrayList<>());
        categoryMap.get(updatedArtwork.getType()).add(updatedArtwork);

        return true;
    }
    public boolean removeArtwork(int id) {
        Artwork artwork = idMap.get(id);
        if (artwork == null) {
            return false;
        }

        artworkSet.remove(artwork);
        idMap.remove(id);

        List<Artwork> categoryList = categoryMap.get(artwork.getType());

        if (categoryList != null) {
            categoryList.remove(artwork);
            if (categoryList.isEmpty()) {
                categoryMap.remove(artwork.getType());
            }
        }

        requestQueue.removeIf(request -> request.getArtwork().getId() == id );

        return true;
    }

    public Artwork getById(int id) {
        return idMap.get(id);
    }

    public List<Artwork> getByCategory(String category) {
        List<Artwork> result = categoryMap.get(category);
        if (result == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(result);
    }

    public List<Artwork> getAllSortedNaturally() {
        List<Artwork> list = new ArrayList<>(artworkSet);
        Collections.sort(list);
        return list;
    }

    public List<Artwork> getAllSorted(Comparator<Artwork> comparator) {
        List<Artwork> list = new ArrayList<>(artworkSet);
        list.sort(comparator);
        return list;
    }

    public boolean submitRequest(User user, Artwork artwork) {
        if (user == null || artwork == null) {
            return false;
        }
        if (!user.getRole().equalsIgnoreCase("User")) {
            return false;
        }
        if (idMap.get(artwork.getId()) != artwork) {
            return false;
        }

        Request request = new Request(user, artwork);
        requestQueue.offer(request);
        System.out.println("Request submitted successfully!");

        return true;
    }

    public List<Request> getPendingRequests() {
        return new ArrayList<>(requestQueue);
    }

    public boolean processNextRequest(User admin, boolean approve) {
        if (admin == null || !admin.getRole().equalsIgnoreCase("Admin")) {

            System.out.println("Only admins can process requests.");
            return false;
        }

        Request request = requestQueue.poll();

        if (request == null) {
            System.out.println("There are no pending requests.");
            return false;
        }

        if (approve) {
            request.approve();
            System.out.println("Request approved!");
        } else {
            request.reject();
            System.out.println("Request rejected!");
        }

        System.out.println(request);

        return true;
    }
    public void printAnalytics() {
        System.out.println(" ARTWORK ANALYTICS: ");
        System.out.println("Total artworks: " + artworkSet.size());
        System.out.println(" Artworks by category: ");

        for (Map.Entry<String, List<Artwork>> entry : categoryMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().size());
        }

        System.out.println(" Pending requests: " + requestQueue.size());

        System.out.println("   ");
    }

    public int getArtworkCount() {
        return artworkSet.size();
    }
}
