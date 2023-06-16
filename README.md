# Statistics Service

It is an application to calculate realtime statistics for the last 60 seconds of transactions.

## How to run

As it is a spring-boot based application, to run we can do:

```mvn spring-boot:run```

The argument informed might be the requested amount.

## Rest APIs

Statistics service provides a rest APIs bellow 

| Http method        |      Path       | Description                                                            | Request Body | Response                                                                                                                                                                                         |
| -------------      |:---------------:|------------------------------------------------------------------------|--------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `GET`      |  `/statistics`  | Returns the statistic based of the transactions of the last 60 seconds | not apllied  | `{ "sum": "1000.00", "avg": "100.53", "max": "200000.49", "min": "50.23", "count": 10 }`                                                                                                         |
| `POST`     | `/transactions` | This endpoint is called to create a new transaction. | `{ "amount": "12.3343", "timestamp": "2018-07-17T09:59:51.312Z" }` | 201 – in case of success. 204 – if the transaction is older than 60 seconds. 400 – if the JSON is invalid. 422 – if any of the fields are not parsable or the transaction date is in the future. |
| `DELETE`   | `/transactions` | This endpoint causes all existing transactions to be deleted | not apllied | 204 status code                                                                                                                                                                                  |
