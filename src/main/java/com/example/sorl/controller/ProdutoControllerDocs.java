package com.example.sorl.controller;

import com.example.sorl.dto.PageDTO;
import com.example.sorl.dto.ProdutoResponseDTO;
import com.example.sorl.entity.Produto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name ="Produtos", description = "Endpoints para gerenciamento e busca de produtos")
public interface ProdutoControllerDocs {

    @Operation(
            summary = "Lista todos os produtos",
            description = "Retorna todos os produtos armazenados no PostgreSQL"
    )
    ResponseEntity<PageDTO<ProdutoResponseDTO>> getAllProdutos(@ParameterObject Pageable pageable);

    @Operation(
            summary = "Buscar produtos (Solr)",
            description = "Realiza busca aproximada de produtos utilizando Apache Solr"
    )
    ResponseEntity<List<ProdutoResponseDTO>> getBuscaSolr(@RequestParam String termo);

    @Operation(
            summary = "Criar produto",
            description = "Cadastra um novo produto no banco de dados"
    )
    ResponseEntity<Produto> createProduto(@RequestBody Produto produto);

    @Operation(
            summary = "Atualizar produto",
            description = "Atualiza os dados de um produto existente"
    )
    ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody Produto produto);

    @Operation(
            summary = "Deletar produto",
            description = "Remove um produto pelo ID"
    )
    ResponseEntity<Void> deleteProduto(@PathVariable Long id);

    @Operation(
            summary = "Buscar produto por ID",
            description = "Retorna um produto específico pelo ID"
    )
    ResponseEntity<Produto> getProdutoById(@PathVariable Long id);
}
