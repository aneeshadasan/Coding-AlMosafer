package utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.SneakyThrows;

public class DateUtils {

    @SneakyThrows
    public LocalDate formatStringToDate(String strDate) {

        DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(strDate, DATEFORMATTER);
        return date;
    }

}
