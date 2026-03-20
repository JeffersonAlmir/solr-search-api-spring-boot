package com.example.sorl.controller;

import com.example.sorl.dto.PageDTO;
import com.example.sorl.dto.ProdutoRequestDTO;
import com.example.sorl.dto.ProdutoResponseDTO;
import com.example.sorl.entity.Produto;
import com.example.sorl.service.ProdutoServiceFacade;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController implements ProdutoControllerDocs{
    private ProdutoServiceFacade produtoServiceFacade;

    public ProdutoController(ProdutoServiceFacade produtoServiceFacade){
        this.produtoServiceFacade = produtoServiceFacade;
    }

    @Override
    @GetMapping
    public ResponseEntity<PageDTO<ProdutoResponseDTO>> getAllProdutos(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(produtoServiceFacade.listar(pageable));
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<List<ProdutoResponseDTO>> getBuscaSolr(@RequestParam String termo){
        return ResponseEntity.status(HttpStatus.OK).body(produtoServiceFacade.listar(termo));
    }

    @Override
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> createProduto(@RequestBody @Valid ProdutoRequestDTO produtoRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoServiceFacade.salvar(produtoRequestDTO));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> updateProduto(@PathVariable Long id, @RequestBody @Valid ProdutoRequestDTO produtoRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(produtoServiceFacade.atualizar(id, produtoRequestDTO));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id){
        produtoServiceFacade.deletar(id);
        return  ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(produtoServiceFacade.getProdutoById(id));
    }
}
