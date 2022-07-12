package by.it.academy.justshooter.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressDto implements Serializable {
    private final Integer id;
    private final Integer shopOwnerId;
    private final String city;
    private final String street;
    private final String streetType;
    private final String buildingNumber;
    private final String officeNumber;
}
