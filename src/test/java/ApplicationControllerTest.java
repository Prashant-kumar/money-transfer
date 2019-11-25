import com.revolut.moneytransfer.config.Errors;
import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.AccountCreateRequest;
import com.revolut.moneytransfer.util.Path;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApplicationControllerTest extends AbstractTestEngine {

    @Test
    public void createdAccountShouldHaverZeroBalance() {
        Account createdAccount = given()
                .body(new AccountCreateRequest("Jack"))
                .put(Path.ACCOUNT)
                .then().body("balance", equalTo(0))
                .body("name", equalTo("Jack")).and()
                .extract().as(Account.class);

        get(Path.ACCOUNT_DETAIL.replace(":uuid", createdAccount.getUuid().toString()))
                .then().body("balance", equalTo(0))
                .body("name", equalTo("Jack")).and()
                .extract().as(Account.class);

    }


    @Test
    public void accountCreationMustThrowExceptionIfNameIsEmptyOrNULL() {
        given()
                .body(new AccountCreateRequest(""))
                .put(Path.ACCOUNT).then()
                .statusCode(400)
                .body("message", equalTo(Errors.ACCOUNT_NAME_IS_REQUIRED));
    }

    @Test
    public void getAccountMustThrowExceptionIfUuidIsInWrongFormat() {
        get(Path.ACCOUNT_DETAIL.replace(":uuid", "abc")).then()
                .statusCode(400)
                .body("message", equalTo(Errors.INVALID_UUID));
    }
}