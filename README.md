 # Money transfer Restful API
 I have created the APIs for following operations.
  * Creating and fetching accounts. 
  * Credit money into account.
  * Transfer money between accounts.

 ## Requirements
 One of the major requirement for this service was that it should be light weight thats why I have chosen the tech stack keeping in mind the memory footprint and througput of librariees used, I have avoided libraries that are known to eat RAM.
 
 Second requirement was regarding the in memory datastore and thread safety.
 
 ## Tech Stack
 * Spark
 * JOOQ
 * H2
 * Flyway
 * Google Guice
 
 ## Approach
 * I am using H2 (In memory) database, every time we start the application it gives us a prestine database.
 * Account, Ledger and Transaction tables are being used to provide a consistent and verifiable data store.
 * Long is the unit of money not Double, so 10.75$ is represented as 1075 cents.
 * For simplicity, I am not taking care of currencies. I am assuming that all accounts deal in same currency.
 
 
 ## Running Locally
 * mvn -Pjooq-generate
 * mvn clean package
 * java -jar ./target/moneytransfer-jar-with-dependencies.jar
 
 ## Running tests
  * mvn -Pjooq-generate
  * mvn clean test
  


 ## APIs


| Request | Response |
| --- | --- |
| curl -X PUT http://localhost:8080/accounts \\<br>-H 'Accept: */*' \\<br>-H 'Content-Type: application/json' \\<br>-H 'Host: localhost:8080' \\<br>-d '{"name": "Prashant"}' | {<br>&nbsp; &nbsp; &nbsp; &nbsp;"uuid" : "8757ff87-16f4-4a84-bb16-a1db7650e467", <br>&nbsp; &nbsp; &nbsp; &nbsp;"name" : "Prashant",<br>&nbsp; &nbsp; &nbsp; &nbsp;"balance" : 0<br>} |
| curl -X PUT http://localhost:8080/accounts \\<br>-H 'Accept: */*' \\<br>-H 'Content-Type: application/json' \\<br>-H 'Host: localhost:8080' \\<br>-d '{"name": "Karan"}' | {<br>&nbsp; &nbsp; &nbsp; &nbsp;"uuid" : "da751052-1cd5-441c-8c85-9e638dc9d3df", <br>&nbsp; &nbsp; &nbsp; &nbsp;"name" : "Karan",<br>&nbsp; &nbsp; &nbsp; &nbsp;"balance" : 0<br>} |
| curl -X POST http://localhost:8080/accounts/8757ff87-16f4-4a84-bb16-a1db7650e467/credit \\<br>-H 'Accept: */*' \\<br>-H 'Content-Type: application/json' \\<br>-H 'Host: localhost:8080' \\<br>-d '{ "amount": 5000 }' | {<br>&nbsp; &nbsp; &nbsp; &nbsp;"uuid" : "8757ff87-16f4-4a84-bb16-a1db7650e467", <br>&nbsp; &nbsp; &nbsp; &nbsp;"name" : "Prashant", <br>&nbsp; &nbsp; &nbsp; &nbsp;"balance" : 5000<br>}|
| curl -X POST http://localhost:8080/accounts/8757ff87-16f4-4a84-bb16-a1db7650e467/transfer \\<br>-H 'Accept: */*' \\<br>-H 'Content-Type: application/json' \\<br>-H 'Host: localhost:8080' \\<br>-d '{"to": "da751052-1cd5-441c-8c85-9e638dc9d3df", "amount": 1000}' | {<br>&nbsp; &nbsp; &nbsp; &nbsp;"uuid" : "8757ff87-16f4-4a84-bb16-a1db7650e467",<br>&nbsp; &nbsp; &nbsp; &nbsp;"name" : "Prashant",<br>&nbsp; &nbsp; &nbsp; &nbsp;"balance" : 4000<br>} |
| curl -X GET http://localhost:8080/accounts/da751052-1cd5-441c-8c85-9e638dc9d3df \\<br>-H 'Accept: */*' \\<br>-H 'Host: localhost:8080' | {<br>&nbsp; &nbsp; &nbsp; &nbsp;"uuid" : "da751052-1cd5-441c-8c85-9e638dc9d3df", <br>&nbsp; &nbsp; &nbsp; &nbsp;"name" : "Karan", <br>&nbsp; &nbsp; &nbsp; &nbsp;"balance" : 1000<br>} |
