JCF collections in ArtworkManager

Set<Artwork> — unique artworks (equals/hashCode)
Map<Integer, Artwork> — O(1) lookup by id
Map<String, List<Artwork>> — group by category
Queue<Request> — FIFO requests, poll()
