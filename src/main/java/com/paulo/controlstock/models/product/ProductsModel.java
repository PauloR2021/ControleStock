package com.paulo.controlstock.models.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "produtos")
@Getter @Setter
public class ProductsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    private String category;

    @Column(nullable = false,name = "preco_compra")
    private Double price;

    @Column(name = "preco_venda")
    private Double venda;

    @Column(nullable = false)
    private Integer stock;
}
