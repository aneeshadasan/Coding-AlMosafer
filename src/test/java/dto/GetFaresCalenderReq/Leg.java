
package dto.GetFaresCalenderReq;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Leg {

    public String originId;
    public String destinationId;
    public String departureFrom;
    public String departureTo;

}
