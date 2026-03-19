package com.example.sorl.service;

import com.example.sorl.dto.PageDTO;
import com.example.sorl.dto.ProdutoResponseDTO;
import com.example.sorl.entity.Produto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProdutoService {
    Produto salvar(Produto produto) ;
    PageDTO<ProdutoResponseDTO> listar(Pageable pageable);
    void deletar(Long id) ;
    Produto atualizar(Long id, Produto produto);

}
