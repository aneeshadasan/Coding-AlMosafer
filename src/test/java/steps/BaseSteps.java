package steps;

import dto.GetCurrencyListRes.CurrencyListDTO;
import dto.GetFaresCalenderReq.GetFaresCalender;
import io.restassured.response.Response;
import utility.DateUtils;

public class BaseSteps {

    public final String BASE_URI = "https://ae.almosafer.com/api";

    public static Response response;
    public static GetFaresCalender getFaresCalenderPayload;
    public static CurrencyListDTO currencyListDTO;

    DateUtils dateUtils = new DateUtils();


}
