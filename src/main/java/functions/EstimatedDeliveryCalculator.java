package functions;

import java.time.LocalDate;

public class EstimatedDeliveryCalculator {
    public static LocalDate calculateEstimatedDeliveryDate(LocalDate orderPlacementDate, int estimatedDeliveryTimeInDays) {
        return orderPlacementDate.plusDays(estimatedDeliveryTimeInDays);
    }

}

