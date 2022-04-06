# Bookstore

## Project Description

You work in a bookstore platform as a software engineer. The bookstore owner would like you to create a simple online
books web api app. The bookstore is online books retail firm which operates only on internet. Main target of the
bookstore is deliver books to their customer within the same day. That is why stock consistency is the first priority
for their vision operations.


## API USAGE

## Customer Controller

```http
   localhost:8080/api/v1
```

| HTTP | URL     | Explanation                |
| :-------- | :------- | :------------------------- |
| `GET` | `/customers` | **Get all customers**. |
| `POST` | `/customer` | **Save customer**. |
| `PUT` | `/customer/{identityNumber}` | **Update customer with given identity number**. |
| `DELETE` | `/customer/{identityNumber}` | **Delete customer with given identity number**. |
| `GET` | `/customer` | **Get customer with given identity number**. |

## Order Controller

```http
  localhost:8080/api/v1
```

| HTTP | URL     | Explanation                       |
| :-------- | :------- | :-------------------------------- |
| `POST`      | `/create` | **Create an order**. |
| `DELETE`      | `/deleteOrder` | **Delete an order**. |
| `GET`      | `/listOfOrders` | **Get all orders**. |
| `GET`      | `/order/{id}` | **Get an order with given order id.**. |

## Book Controller

```http
  localhost:8080/api/v1
```

| HTTP | URL     | Explanation                       |
| :-------- | :------- | :-------------------------------- |
| `POST`      | `/book` | **Add a book.**. |
| `DELETE`      | `/book/{bookName}` | **Delete a book with given book name**. |
| `GET`      | `/books` | **Get all books**. |
| `GET`      | `/book` | **Get a book with given book name**. |


## TO DO 

* `Tests`
* `Security/Authentication`
* `Validations`

## Technologies

* JDK 11
* Spring Boot
* Spring Data JPA
* PostgreSql
* Docker
* Open API