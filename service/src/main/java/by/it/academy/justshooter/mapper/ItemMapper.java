package by.it.academy.justshooter.mapper;

import by.it.academy.justshooter.dto.ItemDto;
import by.it.academy.justshooter.entity.Item;

public class ItemMapper {

    public static ItemDto mapFrom(Item item) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getArticle(),
                item.getBarcode());
    }

}
