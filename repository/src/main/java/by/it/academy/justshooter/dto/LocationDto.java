package by.it.academy.justshooter.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LocationDto implements Serializable {
    private final Integer id;
    private final String shopNumber;
    private final Integer floor;
    private final Integer shopId;
    private final String description;
}
