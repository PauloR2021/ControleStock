package com.paulo.controlstock.dtos.products;

public record ResponseProductsDTO(
        Integer id,
        String name,
        String description,
        String category,
        Double price,
        Double venda,
        Integer stock
) {
}
