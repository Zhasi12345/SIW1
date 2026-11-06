package Model;
public class Request {
    public enum Status {
        PENDING,
        APPROVED,
        REJECTED
    }
    private static int nextId = 1;
    private int requestId;
    private User requester;
    private Artwork artwork;
    private Status status;

    public Request(User requester, Artwork artwork) {
        this.requestId = nextId++;
        this.requester = requester;
        this.artwork = artwork;
        this.status = Status.PENDING;
    }
    public int getRequestId() {
        return requestId;
    }
    public User getRequester() {
        return requester;
    }
    public Artwork getArtwork() {
        return artwork;
    }
    public Status getStatus() {
        return status;
    }
    public void approve() {
        status = Status.APPROVED;
    }
    public void reject() {
        status = Status.REJECTED;
    }
    @Override
    public String toString() {
        return "Request ID: " + requestId
                + " | User: " + requester.getName()
                + " | Artwork: " + artwork.getTitle()
                + " | Status: " + status;
    }
}