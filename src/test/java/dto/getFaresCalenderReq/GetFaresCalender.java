
package dto.getFaresCalenderReq;

import java.util.List;
import lombok.Builder;

@Builder
public class GetFaresCalender {

    public List<Leg> leg = null;

    public String cabin;

    public Pax pax;

    public List<Object> stops = null;

    public List<Object> airline = null;

    public TimeSlots timeSlots;

    public Airports airportsDTO;

}
