# Sale and Inventory Manager Application

This Sales/Inventory Management Application is built using Java and Spring Boot. The application provides REST API endpoints to manage products, inventory, and orders.

## Features
The application supports the following features:

- Create a new product
- Update an existing product
- Get a list of all available products
- Place an Order based on availability of product
- Capture basic customer detail (name and phone number) for the Order
- Change the product's price without affecting the total value of orders that have already been placed
- Publish the basic detail of the created Order to Kafka for reporting

### Prerequisites
* Java 11 or higher
* Apache Kafka 2.8.0 or higher
* Maven 3.6.3 or higher
### Installation
* Clone the repository
* Start Kafka service
* Update the application.properties file with the Kafka server configuration and any other relevant configurations
* Run the application using Maven with the following command:
`mvn spring-boot:run`

#### REST API Endpoints
* Create a new product
 `/api/products/create`<br>
Example request body:<br>
`json
{
"name": "Product A",
"price": 10.00,
"quantityInStock": 100,
"description": "A sample product"
} `
* 
* Update an existing product
 `PUT /api/products//update/{id}`<br>
Example Request Body(json): <br>
`{
"name": "Product A",
"price": 12.00,
"quantity": 100,
"description": "A sample product"
}`

* Get a list of all available products <br>
`GET /api/products/get/{id}`


* Place an Order based on availability of product
`POST /api/orders/create/{productId}`<br>
Example Request Body: <br>
`{
"quantity": 10,
"customerName": "John Smith",
"customerPhone": "555-1234"
}`

* Change the product’s price without affecting the total value of orders which have already been placed
`PUT /api/products/price/change/{id}` ,
Example Request Body:
`{
"price": 15.00
}`


The application will automatically publish the basic detail of the created Order to the configured Kafka topic,
which is then consumed by [this](https://github.com/ife0luwa/Report_Application) kafka consumer