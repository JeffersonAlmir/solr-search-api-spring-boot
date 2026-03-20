package com.example.sorl.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProdutoRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        @Size(max=100)
        String nome,

        @NotNull(message = "Preço é obigatório")
        @Positive(message = "Preço deve ser positivo")
        Double preco
) {
}
