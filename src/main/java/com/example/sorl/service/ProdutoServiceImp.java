package com.example.sorl.service;

import com.example.sorl.dto.PageDTO;
import com.example.sorl.dto.ProdutoRequestDTO;
import com.example.sorl.dto.ProdutoResponseDTO;
import com.example.sorl.entity.Produto;
import com.example.sorl.exceptions.ResourceNotFoundException;
import com.example.sorl.mapper.ProdutoMapper;
import com.example.sorl.repository.ProdutoRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


@Service
public class ProdutoServiceImp  {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoServiceImp(ProdutoMapper produtoMapper, ProdutoRepository produtoRepository) {
        this.produtoMapper = produtoMapper;
        this.produtoRepository = produtoRepository;
    }


    public ProdutoResponseDTO salvar (ProdutoRequestDTO dto){
        Produto produto = produtoMapper.toEntity(dto);
        Produto salvo = produtoRepository.save(produto);
        return produtoMapper.toDTO(salvo);
    }


    public PageDTO<ProdutoResponseDTO> listar(Pageable pageable) {
        Page<ProdutoResponseDTO> produtoResponseDTOPage = produtoRepository
                .findAll(pageable)
                .map(produtoMapper::toDTO);

        return new PageDTO<>(produtoResponseDTOPage);
    }


    public void deletar(Long id) {
        if(!produtoRepository.existsById(id)){
            throw new ResourceNotFoundException("Produto não encontrado");
        }
        produtoRepository.deleteById(id);
    }


    public ProdutoResponseDTO atualizar(Long id, @NonNull ProdutoRequestDTO dto) {
        Produto updateProduto = produtoRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Produto não encontrado"));

        if(dto.nome() != null && !dto.nome().isBlank()){
            updateProduto.setNome(dto.nome());
        }

        if(dto.preco() != null){
            updateProduto.setPreco(dto.preco());
        }
        Produto salvo = produtoRepository.save(updateProduto);

        return produtoMapper.toDTO(salvo);
    }

    public Produto produtoPorId(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Produto não encontrado"));
    }
}
