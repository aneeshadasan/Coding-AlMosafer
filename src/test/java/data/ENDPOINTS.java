package data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ENDPOINTS {
    GET_FARE_CALENDER("/v3/flights/flight/get-fares-calender"),
    GET_CURRENCYLIST("/system/currency/list");

    String endpoint;
}
