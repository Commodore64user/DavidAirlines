# DavidAirlines README

### Why are we doing this? (and other qs)

The main objective of this project is to utilise all the concepts and technologies learnt during the Software Development Digital Bootcamp. Some of the tools utilised in the project include (but are not limited to) Java, Spring, JUnit, MySQL, Postman, GitHub. 

We were expected to write a full CRUD application (including testing) in the span of five days, I expected this to be a difficult yet achievable task as it was my first time creating a whole app from the ground app, or the back end at least.

My exceptions coming in were not to high as I thought I would soon hit walls from which it would take me a long time to get out of, and given the limited time to complete the task I originally focused mainly on completing the bare essentials, but as I went along found I could reel out a bit and ended up adding a couple small classes to the project.

I was happy to have achieved a reservation system where flyers can choose from an existing pool of flights (this happens randomly) and a random string is generated as a reservation number, much like a real life airline booking. I had also other ideas like limiting the number of seats per flights and overbooking (max profits) where passengers are denied boarding (`` ¯\_(ツ)_/¯ ``) if flight is overbooked.

One particular challenge I found was testing my create method for the passenger entity. The string "reservation" is randomly generated upon each passenger booking a flight, as the string is random and different each time it is [hard] to run the integration test as whatever string you give will not match the random generated by the test.

### Test Coverage:
![Test Coverage](https://github.com/Commodore64user/DavidAirlines/blob/Commodore64user-readme/Screenshots/Screenshot%202022-03-11%20at%2014.02.22.png)

### Postman and MySQL database (from terminal)

![Image 2](https://github.com/Commodore64user/DavidAirlines/blob/Commodore64user-readme/Screenshots/Screenshot%202022-03-11%20at%2010.00.42.png)

![Image 3](https://github.com/Commodore64user/DavidAirlines/blob/Commodore64user-readme/Screenshots/Screenshot%202022-03-11%20at%2010.01.30.png)

![Image 4](https://github.com/Commodore64user/DavidAirlines/blob/Commodore64user-readme/Screenshots/Screenshot%202022-03-11%20at%2013.18.22.png)

![Image 5](https://github.com/Commodore64user/DavidAirlines/blob/Commodore64user-readme/Screenshots/Screenshot%202022-03-11%20at%2013.18.45.png)

![Image 6](https://github.com/Commodore64user/DavidAirlines/blob/Commodore64user-readme/Screenshots/Screenshot%202022-03-11%20at%2013.19.08.png)

![Image 7](https://github.com/Commodore64user/DavidAirlines/blob/Commodore64user-readme/Screenshots/Screenshot%202022-03-11%20at%2013.19.44.png)

![Image 8](https://github.com/Commodore64user/DavidAirlines/blob/Commodore64user-readme/Screenshots/Screenshot%202022-03-11%20at%2013.20.01.png)

![Image 9](https://github.com/Commodore64user/DavidAirlines/blob/Commodore64user-readme/Screenshots/Screenshot%202022-03-11%20at%2013.20.25.png)

![Image 10](https://github.com/Commodore64user/DavidAirlines/blob/Commodore64user-readme/Screenshots/Screenshot%202022-03-11%20at%2013.20.38.png)

![Image 11](https://github.com/Commodore64user/DavidAirlines/blob/Commodore64user-readme/Screenshots/Screenshot%202022-03-11%20at%2013.22.50.png)

A link to my Jira board can be found [here](https://daveabc02.atlassian.net/jira/software/projects/QAPROJECT/boards/2)
