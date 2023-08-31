# About project
This project was made based on Hyperskill project [Cinema Room REST Service](https://hyperskill.org/projects/189?track=12).

## Functionality
- Api returns everything in a format of JSON. 
- It uses Tomcat embedded server.

## Endpoints:
**GET** `/seats` returns the number of total rows, colums and a list of available seats. Each seat contains information about its row, column and price. For the first 4 rows price is 10, for the rest price is 8:
```
{
   "total_rows":9,
   "total_columns":9,
   "available_seats":[
      {
         "row":1,
         "column":1,
         "price":10
      },
      {
         "row":1,
         "column":2,
         "price":10
      },
      {
         "row":1,
         "column":3,
         "price":10
      },

      ........

      {
         "row":9,
         "column":8,
         "price":8
      },
      {
         "row":9,
         "column":9,
         "price":8
      }
   ]
}
```

**POST** `/purchase` takes request body with row/column information. 
- If purchase is successful, returns ticket information and unique token:
```
{
    "token": "00ae15f2-1ab6-4a02-a01f-07810b42c0ee",
    "ticket": {
        "row": 1,
        "column": 1,
        "price": 10
    }
}
```

- If seat is alredy taken, returns error with `400 Bad Request` status code:
```
{
    "error": "The ticket has been already purchased!"
}
```

- If user passes incorrect row/column, returns error with `400 Bad Request` status code:
```
{
    "error": "The number of a row or a column is out of bounds!"
}
```

**POST** `/return` takes request body with a token.
- If operation is successful, returns ticket information:
```
{
    "returned_ticket": {
        "row": 1,
        "column": 1,
        "price": 10
    }
}
```

- If token is expired or incorrect, returns error with `400 Bad Request` status code:
```
{
    "error": "Wrong token!"
}
```

**GET** `/stats` has URL parameters: key `password`, value `super_secret`.
- If parameters are correct, returns cinema statistics:
```
{
    "current_income": 0,
    "number_of_available_seats": 81,
    "number_of_purchased_tickets": 0
}
```

- If key/value are incorrect or absent, returns error with `401 Unauthorized` status code:
```
{
    "error": "The password is wrong!"
}
```
