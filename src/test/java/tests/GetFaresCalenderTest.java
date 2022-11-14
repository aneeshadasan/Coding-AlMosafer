package tests;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class GetFaresCalenderTest extends BaseTest {

    @BeforeEach
    public void setInputDate() {
        dateFrom = LocalDate.now().plusDays(20);
        dateTo = LocalDate.now().plusDays(24);
    }

    @Test
    @DisplayName("Verify price is returned for the given dates and Route ")
    public void verifyPriceIsNotNull() {

        getFaresCalenderSteps
                .giveIHaveGetFaresPayloadForOneWay(dateFrom, dateTo)
                .whenICallGetFaresCalender()
                .thenIVerifySuccessResponse()
                .thenIVerifyPriceInTheReponse();
    }

    @Test
    @DisplayName("Should get success for Oneway Flight ")
    public void verifySuccessResponseForOnewayTrip() {

        getFaresCalenderSteps
                .giveIHaveGetFaresPayloadForOneWay(dateFrom, dateTo)
                .whenICallGetFaresCalender()
                .thenIVerifySuccessResponse()
                .thenIVerifyResponseDates();
    }


    @Test
    @DisplayName("Should get success for Round Trip Journey ")
    public void verifySuccessResponseForRoundTrip() {

        getFaresCalenderSteps
                .giveIHaveGetFaresPayloadForRoundTrip(dateFrom, dateTo)
                .whenICallGetFaresCalender()
                .thenIVerifySuccessResponse()
                .thenIVerifyResponseDates();

    }

    @ParameterizedTest
    @ValueSource(strings = {"Economy", "Premium Economy", "Business", "First"})
    @DisplayName("should get success for all types of Cabin class ")
    public void verifySuccessResponseForPayloadWithCabin(String cabin) {

        getFaresCalenderSteps
                .giveIHaveGetFaresPayloadForOneWayWithCabinAs(dateFrom, dateTo, cabin)
                .whenICallGetFaresCalender()
                .thenIVerifySuccessResponse();
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "##Economy"})
    @DisplayName("should get success for all types of Cabin class ")
    public void verifySuccessResponseForPayloadWithInvlidCabin(String cabin) {

        getFaresCalenderSteps
                .giveIHaveGetFaresPayloadForOneWayWithCabinAs(dateFrom, dateTo, cabin)
                .whenICallGetFaresCalender()
                .thenIVerifyErrorResponse();
    }

    @Test
    @DisplayName("Verify that the service responds with Error for Past date ")
    public void verifyErrorResponseForPayloadWithPastDate() {
        getFaresCalenderSteps
                .giveIHaveGetFaresPayloadForOneWay(LocalDate.now().minusDays(10), LocalDate.now().minusDays(5))
                .whenICallGetFaresCalender()
                .thenIVerifyErrorResponse();
    }

}
