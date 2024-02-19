# Mini Rest Project

## Request

We will need a new Java app to read in a CSV file from a directory. The contents will then need to be
sent to a REST API endpoint, in JSON format, and saved to a SQL database.
1. Create a console app to read in a CSV file from a directory.
2. Parse the CSV file of which the contents are:
	- Customer Ref
	- Customer Name
	- Address Line 1
	- Address Line 2
	- Town
	- County
	- Country
	- Postcode
3. Loop through the rows of the CSV file and send each row to a REST API POST endpoint, in
JSON format.
4. The REST API should then save the content to a database table. Format can match the CSV
file.
5. Create a REST API GET endpoint to retrieve the customer information, passing in a customer
ref to filter the data. Contents should be returned in JSON format.
6. Some documentation or Wiki to explain the approach taken.

Where possible, a Test-Driven Development (TDD) approach should be taken.

## Implementation

In order to satisfy the above request, I've split the implementation into two separate modules:
- csv-uploader, which reads a CSV files and posts data to a REST endpoint.
- addressbook, which exposes API endpoints to create and retrieve customer information, and persists the received data in a database.
The approach used in each module is described in the following sections.

### CSV Uploader

CSV uploader is a Maven module written in plain Java. It implements a command-line utility that takes in input the path to a csv file. 

A package called csv and containing one class and two interfaces is in charge of reading and executing operations on the csv file. The path is passed to a CsvFileReader which scans the file line by line. For each line, a CsvLineParser and CsvLineHandler are called to parse the data, and execute operations on it.
CsvLineParser and CsvLineHandler are defined as generic interfaces, so that the three classes contained in the csv package do not need to know which type of content is contained in the csv file, nor what operation is to be done.

A package "customer" contains the classes CustomerParser and CustomerUploader, respectively implementing the interfaces CsvLineParser and CsvLineHandler. CustomerParser transforms the csv string into a stringified JSON object, which will constitute the body of a POST request at later stages. CustomerUploader sends a POST request to the addressbook API.

### Address Book

Address book is the part of this project that deals with persisting and presenting customers' data via a REST API. This module is implemented using spring-boot.
As per common practice, classes are grouped by domain. Since this project only has customers in its domain, a single package called "customer" is created.
The classes contained in this package reflect a typical Controller-Manager-Repository structure.

The controller exposes the following endoints:
- GET /customer/{reference}
- POST /customer

The JSON representation of customers, present in the response of the first endpoint and in the body of the request of the second, is implemented in a POJO class called Customer.

Since there is little business logic to be implemented, the CustomerManager class  deals mainly with the conversion between the Customer object and the CustomerEntity database definition.

In order to avoid boilerplate, I defined a CustomerRepository interface extending the springframework data Repository, and used the standard names to auto-generate the behaviours of the find and save operations.

The database where data is stored is an embedded H2 instance, but can easily be swapped for a different database.

## Example of usage

### Sample data
A very short csv for testing is saved in csv-uploader/src/test/resources/sample.csv .

### Build and run Address Book
Address Book service can be started with the following command:
```bash
cd addressbook
mvn install
mvn spring-boot:run
```

A new customer can be created with a POST request to `localhost:8080/customer`.
Example:
```bash
curl --location 'http://localhost:8080/customer' \
--header 'Content-Type: application/json' \
--data '{
    "reference"    : "sholmes",
    "name"         : "Sherlock Holmes",
    "addressLine1" : "221b Baker street",
    "addressLine2" : "Marylebone",
    "town"         : "London",
    "county"       : "Greater London",
    "country"      : "United Kingdom",
    "postcode"     : "NW1 6XE"
}'
```

Customer information can be retrieved with a GET request to `localhost:8080/customer/{reference}`.
Example:
```bash
curl --location 'http://localhost:8080/customer/sholmes'
```

### Build and run CSV Uploader
Ensure Address Book is up & running before starting the CSV uploader with the following command:
```bash
cd csv-uploader
mvn install
mvn exec:java -Dexec.args="./src/test/resources/sample.csv"
```
