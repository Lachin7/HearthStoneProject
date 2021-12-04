# HearthStoneProject
This is the main project of the Advanced Programming course in my university (Spring 2020).
It got completed in 4 phases during a 8 months period. It is a card game inspired by the features in Hearth Stone game.
## Some of the tools and dependencies used are as follows: 
- **Java**: Almost all the programming is in Java. I used various kinds of structures to have a clean and efficient code.
  - **Client** and **Server** are completely separated. They communicate via **request** and **responses** sent using **JSON**, **TCP** connection and **Visitor**  design pattern. (used ids and made sure they share as least amount of data as possible)
  - **Design Patterns** used: 
     - **Factory** to produce the cards
     - **Singleton** to have access to resource loaders and the main frame, 
     - **Visitor** to have an event-driven system which handled the events corresponding to card actions, passives and etc.
- **SOLID and Clean code** : put so much effort in to having a clean code, separate the logic and models, use Dependency Inversion, have single responsibility and etc.   
- **Hibernate**   

## Other features ##
- **Animation**: Drag and dropping the cards, drawing cards form the deck in an animated way and etc.
- **Reflection**
- Different modes in game: **AI, Practice, Online, Deck Reader, Watch a game**, Golden Time, One shot, Tavern Brawl.
- **Error Handling** in different parts of the game
- Chat box
- logging the different events and saving them to the database
- **MVC**




