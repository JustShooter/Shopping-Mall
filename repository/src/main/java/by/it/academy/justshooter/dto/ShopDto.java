package by.it.academy.justshooter.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShopDto implements Serializable {
    private final Integer id;
    private final String shopName;
    private final Integer locationId;
    private final Integer shopOwnerId;
}
