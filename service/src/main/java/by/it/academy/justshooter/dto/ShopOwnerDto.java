package by.it.academy.justshooter.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@Data
public class ShopOwnerDto implements Serializable {
    private final Integer id;
    private final String ownerName;
    private final AddressDto address;
}
