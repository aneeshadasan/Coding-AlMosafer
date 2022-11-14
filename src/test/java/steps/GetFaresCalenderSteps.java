package steps;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.ENDPOINTS;
import dto.GetFaresCalenderRes.GetFaresCalenderResDTO;
import dto.getFaresCalenderReq.GetFaresCalender;
import dto.getFaresCalenderReq.Leg;
import dto.getFaresCalenderReq.Pax;
import io.qameta.allure.Step;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import org.apache.http.HttpStatus;
import utility.JsonUtils;

public class GetFaresCalenderSteps extends BaseSteps {
    GetFaresCalenderResDTO getFaresCalenderRes;
    public static String payload;
    Boolean withInRangeFlag = false;


    @Step
    public GetFaresCalenderSteps whenICallGetFaresCalender() {

        response =
                given()
                        .baseUri(BASE_URI)
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json, text/javascript")
                        .body(payload)
                        .post(ENDPOINTS.GET_FARE_CALENDER.getEndpoint());
        return this;
    }

    public GetFaresCalenderSteps thenIVerifySuccessResponse() {
        assertEquals(HttpStatus.SC_OK, response.getStatusCode(),
                "GetFaresCalender Response Status is not as expected");

        return this;
    }

    public GetFaresCalenderSteps thenIVerifyErrorResponse() {
        assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusCode(),
                "GetFaresCalender Response Status is not as expected");
        return this;
    }

    public GetFaresCalenderSteps thenIVerifyPriceInTheReponse() {

        String responseBodyString = response.getBody().asString();
        if (!responseBodyString.equals("[]")) {
            try {
                getFaresCalenderRes = new ObjectMapper().readValue(responseBodyString, GetFaresCalenderResDTO.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (Map.Entry entry : getFaresCalenderRes.getFares().entrySet()) {
                assertNotNull(entry.getValue(), "Price is NULL ");
                System.out.println(entry.getKey().toString());
            }
        }
        return this;
    }

    public GetFaresCalenderSteps thenIVerifyResponseDates() {

        String responseBodyString = response.getBody().asString();
        if (!responseBodyString.equals("[]")) {

            try {
                getFaresCalenderRes = new ObjectMapper().readValue(responseBodyString, GetFaresCalenderResDTO.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (Map.Entry entry : getFaresCalenderRes.getFares().entrySet()) {

                String resEntryKey = entry.getKey().toString();
                String[] splittedKeys = resEntryKey.split("_");

                for (int index = 0; index < splittedKeys.length; index++) {
                    verifyDateWithInRange(splittedKeys[index], index);
                }

                assertTrue(withInRangeFlag, "Date is Not within the given input date range");
            }
        }
        return this;
    }


    public void verifyDateWithInRange(String keyDate, int legIndex) {

        LocalDate outputDate = dateUtils.formatStringToDate(keyDate);
        LocalDate inputDateFrom = dateUtils.formatStringToDate(getFaresCalenderPayload.leg.get(legIndex).departureFrom.toString());
        LocalDate inputDateTo = dateUtils.formatStringToDate(getFaresCalenderPayload.leg.get(legIndex).departureTo.toString());

        if (outputDate.isAfter(inputDateFrom.minusDays(1)) && outputDate.isBefore(inputDateTo.plusDays(1))) {
            withInRangeFlag = true;
        } else {
            withInRangeFlag = false;
        }

    }


    public GetFaresCalenderSteps giveIHaveGetFaresPayloadForOneWay(LocalDate dateFrom, LocalDate dateTo) {

        getFaresCalenderPayload = GetFaresCalender
                .builder()
                .leg(Arrays.asList(Leg.builder()
                        .originId("RUH")
                        .destinationId("DXB")
                        .departureFrom(dateFrom.toString())
                        .departureTo(dateTo.toString())
                        .build())
                )
                .cabin("Economy")
                .pax(Pax.builder().adult(1).child(0).infant(0).build())
                .stops(Arrays.asList())
                .airline(Arrays.asList())
                .build();
        payload = JsonUtils.convertObjectToJson(getFaresCalenderPayload);
        return this;

    }

    public GetFaresCalenderSteps giveIHaveGetFaresPayloadForOneWayWithCabinAs(LocalDate dateFrom, LocalDate dateTo, String cabin) {

        getFaresCalenderPayload = GetFaresCalender
                .builder()
                .leg(Arrays.asList(Leg.builder()
                        .originId("RUH")
                        .destinationId("DXB")
                        .departureFrom(dateFrom.toString())
                        .departureTo(dateTo.toString())
                        .build())
                )
                .cabin(cabin)
                .pax(Pax.builder().adult(1).child(0).infant(0).build())
                .stops(Arrays.asList())
                .airline(Arrays.asList())
                .build();
        payload = JsonUtils.convertObjectToJson(getFaresCalenderPayload);
        return this;

    }

    @Step
    public GetFaresCalenderSteps giveIHaveGetFaresPayloadForRoundTrip(LocalDate dateFrom, LocalDate dateTo) {
        getFaresCalenderPayload = GetFaresCalender
                .builder()
                .leg(Arrays.asList(Leg.builder()
                        .originId("RUH")
                        .destinationId("DXB")
                        .departureFrom(dateFrom.toString())
                        .departureTo(dateTo.toString())
                        .build(), Leg.builder()
                        .originId("DXB")
                        .destinationId("RUH")
                        .departureFrom(dateFrom.plusDays(10).toString())
                        .departureTo(dateFrom.plusDays(14).toString())
                        .build())
                )
                .cabin("Economy")
                .pax(Pax.builder().adult(1).child(0).infant(0).build())
                .stops(Arrays.asList())
                .airline(Arrays.asList())
                .build();

        payload = JsonUtils.convertObjectToJson(getFaresCalenderPayload);
        return this;
    }

}
