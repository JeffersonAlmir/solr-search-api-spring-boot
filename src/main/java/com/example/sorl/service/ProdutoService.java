package com.example.sorl.service;

import com.example.sorl.dto.PageDTO;
import com.example.sorl.dto.ProdutoRequestDTO;
import com.example.sorl.dto.ProdutoResponseDTO;
import com.example.sorl.entity.Produto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProdutoService {
    ProdutoResponseDTO salvar(ProdutoRequestDTO dto) ;
    PageDTO<ProdutoResponseDTO> listar(Pageable pageable);
    void deletar(Long id) ;
    ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto);

}
