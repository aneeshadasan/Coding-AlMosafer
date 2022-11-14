
package dto.GetCurrencyListRes;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.annotation.Generated;


@Generated("jsonschema2pojo")
public class BaseDTO implements Serializable
{

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
    private final static long serialVersionUID = -1535244532437688033L;

}
