package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GetCurrencyListTest extends BaseTest {

    @Test
    @DisplayName("verify that Get CurrencyList service is responding with 200 - success response ")
    public void verifyGetCurrencyListSuccessResponse() {
        getCurrencyListSteps
                .whenICallGetCurrencyList()
                .thenIVerifySuccessResponse();
    }

    @Test
    @DisplayName("Validate the Fields for each Currency is not null in the response")
    public void verifyGetCurrencyList() {

        getCurrencyListSteps
                .whenICallGetCurrencyList()
                .thenIVerifySuccessResponse()
                .thenIVerifyResponse();
    }

    @Test
    @DisplayName("Rate should be 1 for in the for the Base Currency in the response")
    public void verifyRateForTheBaseCurrency() {

        getCurrencyListSteps
                .whenICallGetCurrencyList()
                .thenIVerifySuccessResponse()
                .thenIVerifyResponse()
                .verifyRateForBasecurrency();

    }
}
