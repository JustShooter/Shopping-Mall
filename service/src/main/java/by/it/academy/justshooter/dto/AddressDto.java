package by.it.academy.justshooter.dto;

import by.it.academy.justshooter.entity.enums.StreetType;
import lombok.Data;

import java.io.Serializable;

@Data
public class AddressDto implements Serializable {
    private final Integer id;
    private final String city;
    private final String street;
    private final StreetType streetType;
    private final String buildingNumber;
    private final String officeNumber;
}
