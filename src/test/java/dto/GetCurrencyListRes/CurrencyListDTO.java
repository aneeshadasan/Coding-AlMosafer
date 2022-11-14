
package dto.GetCurrencyListRes;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonProperty;


@Generated("jsonschema2pojo")
public class CurrencyListDTO implements Serializable {

    @JsonProperty("base")
    public BaseDTO baseDTO;
    @JsonProperty("equivalent")
    public List<EquivalentDTO> equivalentDTO = null;
    @JsonProperty("failed")
    public List<Object> failed = null;


}
