package coba.daily.you.model.dto;

import coba.daily.you.model.entity.Product;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductDto {
    private Integer id;
    private @NotNull String productName;
    private @NotNull Integer stock;
    private @NotNull Double price;
    private @NotNull String pictureUrl;
    private @NotNull Integer idCategory;
    private @NotNull String categoryName;


}