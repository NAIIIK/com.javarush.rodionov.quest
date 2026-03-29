# Quest Web-game

Final project for module 3 JavaRush University

---

## Stack

- Java 11+
- Jakarta Servlet API 6.0
- Apache Tomcat 10
- Jackson (JSON parsing)
- JSTL
- JUnit 5 + Mockito (tests)

---

## Setup & Run

1. Clone the repository
2. Build with Maven: `mvn clean package`
3. Deploy the `.war` file to Tomcat 10
4. Open in browser: `http://localhost:8080/quest`

---

## Application Context
    
    /quest

---

## API

| Method | URL                          | Description                        |
|--------|------------------------------|------------------------------------|
| GET    | /quest                       | Welcome page                       |
| GET    | /quest/start                 | Nickname form (new player)         |
| POST   | /quest/start?nickname=VALUE  | Submit nickname and start the game |
| GET    | /quest/game                  | Current question page              |
| POST   | /quest/answer?answerId=ID    | Submit answer and go to next step  |
| GET    | /quest/error                 | Error page                         |