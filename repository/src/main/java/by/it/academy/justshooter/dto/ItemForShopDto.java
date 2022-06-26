package by.it.academy.justshooter.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class ItemForShopDto implements Serializable {
    private final Integer id;
    private final String name;
    private final String article;
    private final Long barcode;
    private final Double priceValue;
    private final Double discountPercentage;
    private final Date startDate;
    private final Date endDate;
}
