# Workshop Spring Boot MongoDB

[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://github.com/PeeterDalVitt/workshop-springboot-mongodb/blob/main/LICENSE)

A small RESTful API built with Spring Boot 3.x and MongoDB, showcasing a simple social network backend where you can manage:

- Users
- Posts
- Comments (embedded in posts)

Each Post references an Author (User) and may have several Comments, each with its own Author info. Perfect to explore basic relationships in NoSQL databases.

---

## ✅ Technologies Used

- Java 21
- Spring Boot 3.5.x
- Spring Data MongoDB
- Maven
- MongoDB
- Postman for testing

---

## 🚀 How to Run Locally

1. Make sure MongoDB is installed and running locally.
   - Example command (Windows):
     ```
     mongod --dbpath D:\MongoData\
     ```

2. Clone the repository:

    ```bash
    git clone https://github.com/PeeterDalVitt/workshop-springboot-mongodb.git
    cd workshop-springboot-mongodb
    ```

3. Build and run:

    ```bash
    ./mvnw spring-boot:run
    ```

   Or run the `WorkshopmongoApplication` class from your IDE.

The app starts on:

```
http://localhost:8080
```

---

## 🌐 Available Endpoints

Below are all available API endpoints you can test in Postman.

---

### 📁 User Endpoints

#### Get all users

```
GET /users
```

Returns a list of all users:

```json
[
  {
    "id": "686695738b7177260efa5c2b",
    "name": "Maria Brown",
    "email": "maria@gmail.com"
  },
  ...
]
```

---

#### Get a user by ID

```
GET /users/{id}
```

Example:

```
GET /users/686695738b7177260efa5c2b
```

---

#### Delete a user

```
DELETE /users/{id}
```

Example:

```
DELETE /users/686695738b7177260efa5c2b
```

---

#### Update a user

```
PUT /users/{id}
```

Example request body:

```json
{
  "name": "Maria Brown Updated",
  "email": "maria_updated@gmail.com"
}
```

---

#### Get posts authored by a user

```
GET /users/{id}/posts
```

Example:

```
GET /users/686695738b7177260efa5c2b/posts
```

Returns all posts authored by the user.

---

### 📝 Post Endpoints

#### Get a post by ID

```
GET /posts/{id}
```

Example:

```
GET /posts/686695738b7177260efa5d5a
```

Returns:

```json
{
  "id": "686695738b7177260efa5d5a",
  "date": "2025-07-03T14:45:30.000+00:00",
  "title": "Traveling to New Zealand",
  "body": "I'm going to visit this wonderful country!",
  "author": {
    "id": "686695738b7177260efa5c2b",
    "name": "Maria Brown"
  },
  "comments": [
    {
      "text": "Safe travels!",
      "date": "2025-07-03T14:50:00.000+00:00",
      "author": {
        "id": "686695738b7177260efa5c2d",
        "name": "Bob Grey"
      }
    }
  ]
}
```

---

#### Search posts by title

```
GET /posts/titlesearch?text={searchText}
```

Example:

```
GET /posts/titlesearch?text=Travel
```

---

#### Full search (text + date range)

This endpoint works as a powerful alternative to "get all posts", returning all posts if you omit the filters.

```
GET /posts/fullsearch?text={text}&minDate={minDate}&maxDate={maxDate}
```

- **text** → text to search in posts
- **minDate** → minimum date (format: `yyyy-MM-dd`)
- **maxDate** → maximum date (format: `yyyy-MM-dd`)

Example (fetch all posts with or without filters):

```
GET /posts/fullsearch?text=&minDate=&maxDate=
```

Or to search:

```
GET /posts/fullsearch?text=New&minDate=2025-01-01&maxDate=2025-12-31
```

---

## 🗄️ Data Model Overview

A **Post** document looks like this in MongoDB:

```json
{
  "_id": ObjectId("686695738b7177260efa5d5a"),
  "date": ISODate("2025-07-03T14:45:30Z"),
  "title": "Traveling to New Zealand",
  "body": "I'm going to visit this wonderful country!",
  "author": {
    "id": "686695738b7177260efa5c2b",
    "name": "Maria Brown"
  },
  "comments": [
    {
      "text": "Safe travels!",
      "date": ISODate("2025-07-03T14:50:00Z"),
      "author": {
        "id": "686695738b7177260efa5c2d",
        "name": "Bob Grey"
      }
    }
  ]
}
```

It looks especially good in **MongoDB Compass**, since everything (post, author, and comment authors) is structured clearly in a single document. Very readable and easy to explore visually!

---

## ⚖️ License

[MIT License](https://github.com/PeeterDalVitt/workshop-springboot-mongodb/blob/main/LICENSE)

---

## ✨ Author

[Peeter Dal Vitt](https://github.com/PeeterDalVitt)

---

> Feel free to fork, test, and extend this workshop!
