package com.revolut.test.unit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revolut.moneytransfer.config.Errors;
import com.revolut.moneytransfer.exceptions.AccountNumberCanNotBeEmptyException;
import com.revolut.moneytransfer.exceptions.AmountIsNegativeException;
import com.revolut.moneytransfer.exceptions.NameCanNotBeEmptyException;
import com.revolut.moneytransfer.util.RequestValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RequestValitatorTest {
    @Test
    public void accountRequestMustHaveName() throws JsonProcessingException {
        Assertions.assertThatExceptionOfType(NameCanNotBeEmptyException.class)
                .isThrownBy(() -> {RequestValidator.getNewAccount("{}"); })
                .withMessage(Errors.ACCOUNT_NAME_IS_REQUIRED);
    }

    @Test
    public void amountMustNotBeNegative() throws JsonProcessingException {
        Assertions.assertThatExceptionOfType(AmountIsNegativeException.class)
                .isThrownBy(() -> {RequestValidator.getCreditTransactionRequest("{\"amount\": -35}"); })
                .withMessage(Errors.AMOUNT_IS_NEGATIVE);

        Assertions.assertThatExceptionOfType(AmountIsNegativeException.class)
                .isThrownBy(() -> {RequestValidator.getMoneyTransferRequest("{\"amount\": -35}"); })
                .withMessage(Errors.AMOUNT_IS_NEGATIVE);
    }

    @Test
    public void toAccountUuidMustBePresent() throws JsonProcessingException {

        Assertions.assertThatExceptionOfType(AccountNumberCanNotBeEmptyException.class)
                .isThrownBy(() -> {RequestValidator.getMoneyTransferRequest("{\"amount\": 75}"); })
                .withMessage(Errors.ACCOUNT_NUMBER_IS_REQUIRED);
    }


}
