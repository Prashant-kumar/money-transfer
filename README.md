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
 * mvn clean package
 * java -jar ./target/moneytransfer-jar-with-dependencies.jar
 
 ## Running tests
  * mvn clean test
  
  
## Pitfalls
If you can't run mvn clean package command and mvn is failing at the compilation step. Most likely you are facing this because of jooq's generated files. Please run **mvn -Pjooq-generate** to resolve this.

## APIs   