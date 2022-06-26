package by.it.academy.justshooter.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShopOwnerDto implements Serializable {
    private final Integer id;
    private final String ownerName;
    private final Integer addressId;
}
