package by.it.academy.justshooter.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class DiscountDto implements Serializable {
    private final Integer id;
    private final Double discountPercentage;
    private final Date startDate;
    private final Date endDate;
}
