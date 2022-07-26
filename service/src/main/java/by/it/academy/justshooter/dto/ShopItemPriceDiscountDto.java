package by.it.academy.justshooter.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class ShopItemPriceDiscountDto implements Serializable {
    private final String itemName;
    private final String itemArticle;
    private final String itemBarcode;
    private final Double pricePriceValue;
    private final String shopShopName;
    private final String shopLocationShopNumber;
    private final Integer shopLocationFloor;
    private final String shopLocationDescription;
    private final Double discountDiscountPercentage;
    private final Date discountStartDate;
    private final Date discountEndDate;
}
