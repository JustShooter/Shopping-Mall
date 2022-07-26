package by.it.academy.justshooter.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDto implements Serializable {
    private final Integer id;
    private final String name;
    private final String description;
}
