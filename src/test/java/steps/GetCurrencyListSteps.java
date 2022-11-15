package steps;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import data.ENDPOINTS;
import dto.GetCurrencyListRes.CurrencyListDTO;
import io.qameta.allure.Step;
import org.apache.http.HttpStatus;

public class GetCurrencyListSteps extends BaseSteps {
/* To Call Get currency list service" */
    @Step
    public GetCurrencyListSteps whenICallGetCurrencyList() {

        response =
                given()
                        .baseUri(BASE_URI)
                        .get(ENDPOINTS.GET_CURRENCYLIST.getEndpoint());
        return this;
    }

/* To verify response status of  Get currency list service" */
    @Step
    public GetCurrencyListSteps thenIVerifySuccessResponse() {

        assertEquals(HttpStatus.SC_OK, response.getStatusCode(),
                "GetCurrencyList Response Status is not as expected");

        return this;
    }
    /* To verify the fields in the response of  Get currency list service" */
    @Step
    public GetCurrencyListSteps thenIVerifyResponse() {

        currencyListDTO = response.as(CurrencyListDTO.class);

        assertNotNull(currencyListDTO.baseDTO.code);
        assertNotNull(currencyListDTO.baseDTO.symbol);
        assertNotNull(currencyListDTO.baseDTO.decimalDigits);
        assertNotNull(currencyListDTO.baseDTO.name);
        assertNotNull(currencyListDTO.baseDTO.rounding);

        currencyListDTO.equivalentDTO.stream()
                .forEach(eqvtCur -> {
                            assertAll(
                                    () -> assertNotNull(eqvtCur.symbol),
                                    () -> assertNotNull(eqvtCur.name),
                                    () -> assertNotNull(eqvtCur.nameAr),
                                    () -> assertNotNull(eqvtCur.decimalDigits),
                                    () -> assertNotNull(eqvtCur.rounding),
                                    () -> assertNotNull(eqvtCur.code),
                                    () -> assertNotNull(eqvtCur.namePlural),
                                    () -> assertNotNull(eqvtCur.rate),
                                    () -> assertNotNull(eqvtCur.date)
                            );
                        }
                );
        return this;
    }

    public GetCurrencyListSteps verifyRateForBasecurrency() {

        assertEquals(1, currencyListDTO.equivalentDTO.stream()
                .filter(eqvt -> eqvt.code.equals(currencyListDTO.baseDTO.code))
                .map(cur -> cur.rate).findFirst().get());
        return this;
    }
}
