import com.revolut.moneytransfer.config.Errors;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.AccountCreateRequest;
import com.revolut.moneytransfer.model.CreditTransactionRequest;
import com.revolut.moneytransfer.model.MoneyTransferRequest;
import com.revolut.moneytransfer.util.Path;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TransactionControllerTest extends AbstractTestEngine {

    @Test
    public void credit100MustIncreaseTheBalanceBy100() {
        Account createdAccount = given()
                .body(new AccountCreateRequest("Bill"))
                .put(Path.ACCOUNT)
                .then().statusCode(HttpStatus.CREATED_201)
                .extract().as(Account.class);

        given()
                .body(CreditTransactionRequest.of(100L))
                .post(Path.ACCOUNT_CREDIT.replace(":uuid", createdAccount.getUuid().toString()))
                .then().statusCode(200)
                .body("balance", equalTo(100))
                .body("name", equalTo("Bill"));
    }

    @Test
    public void creditNegative100MustthrowException() {
        Account createdAccount = given()
                .body(new AccountCreateRequest("Jimmy"))
                .put(Path.ACCOUNT)
                .then().statusCode(HttpStatus.CREATED_201)
                .extract().as(Account.class);

        given()
                .body(CreditTransactionRequest.of(-100L))
                .post(Path.ACCOUNT_CREDIT.replace(":uuid", createdAccount.getUuid().toString())).then()
                .statusCode(HttpStatus.BAD_REQUEST_400)
                .body("message", equalTo(Errors.AMOUNT_IS_NEGATIVE));
    }


    @Test
    public void transfer100MustIncreaseandDecreaseTheBalanceBy100() {
        Account fromAccount = given()
                .body(new AccountCreateRequest("Alice"))
                .put(Path.ACCOUNT)
                .then().statusCode(HttpStatus.CREATED_201)
                .extract().as(Account.class);

        given()
                .body(CreditTransactionRequest.of(100L))
                .post(Path.ACCOUNT_CREDIT.replace(":uuid", fromAccount.getUuid().toString()))
                .then().statusCode(200)
                .body("balance", equalTo(100))
                .body("name", equalTo("Alice"));

        Account toAccount = given()
                .body(new AccountCreateRequest("Bob"))
                .put(Path.ACCOUNT)
                .then().statusCode(HttpStatus.CREATED_201)
                .extract().as(Account.class);

        given()
                .body(MoneyTransferRequest.of(toAccount.getUuid(),100L))
                .post(Path.TRANSFER_MONEY.replace(":uuid", fromAccount.getUuid().toString()))
                .then().statusCode(200)
                .body("balance", equalTo(0))
                .body("name", equalTo("Alice"));

        get(Path.ACCOUNT_DETAIL.replace(":uuid", toAccount.getUuid().toString()))
                .then().body("balance", equalTo(100))
                .body("name", equalTo("Bob"));
    }


    @Test
    public void transferShouldThrowExceptionIfSourceAccountDoesNotExist() {


        Account toAccount = given()
                .body(new AccountCreateRequest("Bob1"))
                .put(Path.ACCOUNT)
                .then().statusCode(HttpStatus.CREATED_201)
                .extract().as(Account.class);

        given()
                .body(MoneyTransferRequest.of(toAccount.getUuid(),100L))
                .post(Path.TRANSFER_MONEY.replace(":uuid", "831fd089-b3b2-4343-aab2-de878edc4512"))
                .then().statusCode(HttpStatus.NOT_FOUND_404)
                .body("message", equalTo(Errors.ACCOUNT_NOT_FOUND));
    }

    @Test
    public void transferShouldThrowExceptionIfDestinationAccountDoesNotExist() {
        Account fromAccount = given()
                .body(new AccountCreateRequest("Alice1"))
                .put(Path.ACCOUNT)
                .then().statusCode(HttpStatus.CREATED_201)
                .extract().as(Account.class);

        given()
                .body(CreditTransactionRequest.of(100L))
                .post(Path.ACCOUNT_CREDIT.replace(":uuid", fromAccount.getUuid().toString()))
                .then().statusCode(200)
                .body("balance", equalTo(100))
                .body("name", equalTo("Alice1"));


        given()
                .body(MoneyTransferRequest.of(UUID.fromString("831fd089-b3b2-4343-aab2-de878edc4512"),100L))
                .post(Path.TRANSFER_MONEY.replace(":uuid", fromAccount.getUuid().toString()))
                .then().statusCode(HttpStatus.NOT_FOUND_404)
                .body("message", equalTo(Errors.ACCOUNT_NOT_FOUND));


    }


    @Test
    public void transferShouldThrowExceptionIfAmountIsNegativeOrZero() {
        Account fromAccount = given()
                .body(new AccountCreateRequest("Alice2"))
                .put(Path.ACCOUNT)
                .then().statusCode(HttpStatus.CREATED_201)
                .extract().as(Account.class);

        given()
                .body(CreditTransactionRequest.of(100L))
                .post(Path.ACCOUNT_CREDIT.replace(":uuid", fromAccount.getUuid().toString()))
                .then().statusCode(200)
                .body("balance", equalTo(100))
                .body("name", equalTo("Alice2"));

        Account toAccount = given()
                .body(new AccountCreateRequest("Bob2"))
                .put(Path.ACCOUNT)
                .then().statusCode(HttpStatus.CREATED_201)
                .extract().as(Account.class);

        given()
                .body(MoneyTransferRequest.of(toAccount.getUuid(),-100L))
                .post(Path.TRANSFER_MONEY.replace(":uuid", fromAccount.getUuid().toString()))
                .then().statusCode(HttpStatus.BAD_REQUEST_400)
                .body("message", equalTo(Errors.AMOUNT_IS_NEGATIVE));

    }


    @Test
    public void transferShouldThrowExceptionIfBalanceIsInsufficient() {
        Account fromAccount = given()
                .body(new AccountCreateRequest("Alice3"))
                .put(Path.ACCOUNT)
                .then().statusCode(HttpStatus.CREATED_201)
                .extract().as(Account.class);

        given()
                .body(CreditTransactionRequest.of(100L))
                .post(Path.ACCOUNT_CREDIT.replace(":uuid", fromAccount.getUuid().toString()))
                .then().statusCode(200)
                .body("balance", equalTo(100))
                .body("name", equalTo("Alice3"));

        Account toAccount = given()
                .body(new AccountCreateRequest("Bob3"))
                .put(Path.ACCOUNT)
                .then().statusCode(HttpStatus.CREATED_201)
                .extract().as(Account.class);

        given()
                .body(MoneyTransferRequest.of(toAccount.getUuid(),101L))
                .post(Path.TRANSFER_MONEY.replace(":uuid", fromAccount.getUuid().toString()))
                .then().statusCode(HttpStatus.BAD_REQUEST_400)
                .body("message", equalTo(Errors.INSUFFICIENT_BALANCE));

    }
}
