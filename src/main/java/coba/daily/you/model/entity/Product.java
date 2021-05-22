package coba.daily.you.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import coba.daily.you.model.dto.ProductDto;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Table(name= Product.TABLE_NAME)
@Data
public class Product {
    public static final String TABLE_NAME = "t_product";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME)
    @SequenceGenerator(name = TABLE_NAME, sequenceName = "t_product_seq")
    private Integer id;

    private @NotNull String productName;

    private @NotNull Integer stock;

    private @NotNull Double price;

    private @NotNull String pictureUrl;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_category",  nullable = false, insertable = false, updatable = false)
    private ProductCategory productCategory;

    @Column(name = "id_category_name", nullable = false)
    private Integer idCategory;


}

