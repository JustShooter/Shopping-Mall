package by.it.academy.justshooter.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PriceDto implements Serializable {
    private final Integer id;
    private final Double priceValue;
}
