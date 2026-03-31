package com.paulo.controlstock.dtos.products;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record RequestProductsDTO(

        @NotBlank(message = "Nome não pode estar em branco")
        String name,

        @NotBlank(message = "Descrição não pode estar em branco")
        String description,

        String category,

        @NotNull(message = "O preço de compra é obrigatório")
        @Positive(message = "O preço de compra deve ser maior que Zero")
        Double price,

        @Positive(message = "O preço de vendas deve ser maior que Zero")
        Double venda,

        @NotNull(message = "O estoque é obrigatório")
        @PositiveOrZero(message = "O estoque não pode ser negativo")
        Integer stock
) {
}
