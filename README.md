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
. <img width="819" height="621" alt="image" src="https://github.com/user-attachments/assets/1b15ed6b-8727-4975-91bc-b0a3c74a71cf" />


3. Collections I Used


•	Set<Artwork>: stores artworks without duplicates.

•	Map<Integer, Artwork>: finds an artwork by id very fast.

•	Map<String, List<Artwork>>: groups artworks by category.

•	Queue<Request>: keeps requests in order. The first request in is the first one processed (FIFO).

5. equals, hashCode and Sorting

•	equals() and hashCode() compare artworks by id, so two artworks with the same id are treated as the same.

•	Artwork implements Comparable, so artworks have a natural order.

•	There are 3 comparators for sorting: by title, by price and by artist.


6. What the Program Can Do

•	Add, update and remove artworks. All collections change together.

•	Find an artwork by id or by category.

•	Sort artworks in 3 ways.

•	A user sends a request, and the admin takes the next one with poll() and approves or rejects it.

•	Print simple statistics.

•	Handle errors, such as an empty list, a missing id or a wrong menu choice.

7. Screenshots
<img width="331" height="641" alt="image" src="https://github.com/user-attachments/assets/246fa303-9a02-48f6-bd01-d26571e4ba4f" />

<img width="819" height="607" alt="image" src="https://github.com/user-attachments/assets/0382ebfd-2388-4b6e-ac06-06b90dda0b07" />
<img width="808" height="438" alt="image" src="https://github.com/user-attachments/assets/0e5b4945-99fb-4fa3-aa4c-76d33825ae41" />

<img width="681" height="622" alt="image" src="https://github.com/user-attachments/assets/ebda58c3-d0ae-440f-8f31-e0b893cc23d1" />

<img width="746" height="626" alt="image" src="https://github.com/user-attachments/assets/caa1b440-1201-4499-8119-abb4afc7f39b" />
<img width="616" height="578" alt="image" src="https://github.com/user-attachments/assets/e9f381e9-9380-4394-bdee-4bcb126456e2" />

<img width="293" height="187" alt="image" src="https://github.com/user-attachments/assets/9b597bbb-5fc1-4df0-9cd8-72fdd606c3bc" />

 <img width="398" height="290" alt="image" src="https://github.com/user-attachments/assets/e3baf833-e5ad-4483-afcb-3512c8baed4b" />

7. Conclusion
   
In this work, I moved my Artwork Management System from simple arrays to the Java Collection Framework.

I learned that each collection has its own job:

•	Set keeps artworks unique. It works because equals() and hashCode() are overridden together and use the id.

•	Map finds an artwork by id very fast and groups artworks by category.

•	Queue keeps requests in FIFO order. poll() takes the next request.

•	Comparable and Comparator let me sort artworks in different ways.

The most important point was keeping all collections synchronized. When I add, update or remove an artwork, I change the set and both maps together, so the data stays correct.

The program also shows how two roles work.
A user sends a request, and an admin takes it from the queue and approves or rejects it. 
I added error handling for empty collections, missing ids and wrong menu input.


JCF collections in ArtworkManager

Set<Artwork> — unique artworks (equals/hashCode)
Map<Integer, Artwork> — O(1) lookup by id
Map<String, List<Artwork>> — group by category
Queue<Request> — FIFO requests, poll()
