import com.revolut.moneytransfer.config.Errors;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.AccountCreateRequest;
import com.revolut.moneytransfer.model.CreditTransactionRequest;
import com.revolut.moneytransfer.util.Path;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.Test;

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
}
