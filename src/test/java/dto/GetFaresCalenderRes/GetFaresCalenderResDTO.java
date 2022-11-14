
package dto.GetFaresCalenderRes;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetFaresCalenderResDTO {

    Map<String, PriceDTO> fares = new LinkedHashMap<String, PriceDTO>();

    @JsonAnySetter
    void setFares(String key, PriceDTO value) {
        fares.put(key, value);
    }

}
