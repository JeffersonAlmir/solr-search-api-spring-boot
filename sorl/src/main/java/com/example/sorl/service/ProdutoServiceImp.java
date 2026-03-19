package com.example.sorl.service;

import com.example.sorl.dto.PageDTO;
import com.example.sorl.dto.ProdutoResponseDTO;
import com.example.sorl.entity.Produto;
import com.example.sorl.exceptions.ResourceNotFoundException;
import com.example.sorl.mapper.ProdutoMapper;
import com.example.sorl.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class ProdutoServiceImp implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoServiceImp(ProdutoMapper produtoMapper, ProdutoRepository produtoRepository) {
        this.produtoMapper = produtoMapper;
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto salvar(Produto produto){
        return produtoRepository.save(produto);
    }

    @Override
    public PageDTO<ProdutoResponseDTO> listar(Pageable pageable) {
        Page<ProdutoResponseDTO> produtoResponseDTOPage = produtoRepository
                .findAll(pageable)
                .map(produtoMapper::toDTO);

        return new PageDTO<>(produtoResponseDTOPage);
    }

    @Override
    public void deletar(Long id) {
        if(!produtoRepository.existsById(id)){
            throw new ResourceNotFoundException("Produto não encontrado");
        }
        produtoRepository.deleteById(id);
    }

    @Override
    public Produto atualizar(Long id,Produto produto) {
        Produto updateProduto = produtoRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Produto não encontrado"));

        if(produto.getNome() != null && !produto.getNome().isBlank()){
            updateProduto.setNome(produto.getNome());
        }

        if(produto.getPreco() != null){
            updateProduto.setPreco(produto.getPreco());
        }
        return  produtoRepository.save(updateProduto);
    }

    public Produto produtoPorId(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Produto não encontrado"));
    }
}
