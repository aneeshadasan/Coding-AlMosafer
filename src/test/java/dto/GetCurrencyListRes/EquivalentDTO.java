
package dto.GetCurrencyListRes;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class EquivalentDTO implements Serializable {

    @JsonProperty("symbol")
    public String symbol;
    @JsonProperty("name")
    public String name;
    @JsonProperty("name_ar")
    public String nameAr;
    @JsonProperty("symbol_native")
    public String symbolNative;
    @JsonProperty("decimal_digits")
    public Integer decimalDigits;
    @JsonProperty("rounding")
    public Integer rounding;
    @JsonProperty("code")
    public String code;
    @JsonProperty("name_plural")
    public String namePlural;
    @JsonProperty("rate")
    public Integer rate;
    @JsonProperty("Rate")
    public Integer Rate;
    @JsonProperty("date")
    public String date;
    private final static long serialVersionUID = -142418274105738066L;

}
