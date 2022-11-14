package tests;

import java.time.LocalDate;
import steps.GetCurrencyListSteps;
import steps.GetFaresCalenderSteps;

public class BaseTest {
    LocalDate dateFrom;
    LocalDate dateTo;

    GetFaresCalenderSteps getFaresCalenderSteps = new GetFaresCalenderSteps();
    GetCurrencyListSteps getCurrencyListSteps = new GetCurrencyListSteps();
}
