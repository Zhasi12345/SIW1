Artwork Management System
Student: Sysdygaalieva Zhasmin
id: 250142006
1. About the Project
This is a console program for managing artworks. There are two types of artwork:
•	DigitalArt has a file format.
•	PhysicalArt has a material.
Every artwork has an id, a title, an artist and a price.
There are two roles:
•	User signs in and sends requests.
•	Admin manages artworks and processes requests.

2. UML Diagram
 ![Uploading Artwork_Management.UML.svg…]()

3. Collections I Used
•	Set<Artwork>: stores artworks without duplicates.
•	Map<Integer, Artwork>: finds an artwork by id very fast.
•	Map<String, List<Artwork>>: groups artworks by category.
•	Queue<Request>: keeps requests in order. The first request in is the first one processed (FIFO).

4. equals, hashCode and Sorting
•	equals() and hashCode() compare artworks by id, so two artworks with the same id are treated as the same.
•	Artwork implements Comparable, so artworks have a natural order.
•	There are 3 comparators for sorting: by title, by price and by artist.


5. What the Program Can Do
•	Add, update and remove artworks. All collections change together.
•	Find an artwork by id or by category.
•	Sort artworks in 3 ways.
•	A user sends a request, and the admin takes the next one with poll() and approves or rejects it.
•	Print simple statistics.
•	Handle errors, such as an empty list, a missing id or a wrong menu choice.




6. Screenshots
•	Sign in: 

•	Add artwork: 


•	Search: 












•	Sorting:  
•	Send request: 
 
•	Process request:  
•	Statistics: 
 
7. Conclusion
In this work, I moved my Artwork Management System from simple arrays to the Java Collection Framework. The classes from my UML diagram (Artwork, DigitalArt, PhysicalArt, User, Request) now work with collections in the service layer.
I learned that each collection has its own job:
•	Set keeps artworks unique. It works because equals() and hashCode() are overridden together and use the id.
•	Map finds an artwork by id very fast and groups artworks by category.
•	Queue keeps requests in FIFO order. poll() takes the next request.
•	Comparable and Comparator let me sort artworks in different ways.
The most important point was keeping all collections synchronized. When I add, update or remove an artwork, I change the set and both maps together, so the data stays correct.
The program also shows how two roles work. A user sends a request, and an admin takes it from the queue and approves or rejects it. I added error handling for empty collections, missing ids and wrong menu input.



JCF collections in ArtworkManager

Set<Artwork> — unique artworks (equals/hashCode)
Map<Integer, Artwork> — O(1) lookup by id
Map<String, List<Artwork>> — group by category
Queue<Request> — FIFO requests, poll()
