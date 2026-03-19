package com.example.sorl.service;

import com.example.sorl.dto.PageDTO;
import com.example.sorl.dto.ProdutoResponseDTO;
import com.example.sorl.dto.ProdutoSolrDTO;
import com.example.sorl.entity.Produto;
import com.example.sorl.mapper.ProdutoMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProdutoServiceFacade implements ProdutoService{

    private final DocumentSolrService documentSolrService;
    private final ProdutoServiceImp produtoServiceImp;
    private final ProdutoMapper produtoMapper;

    public ProdutoServiceFacade(DocumentSolrService documentSolrService, ProdutoServiceImp produtoServiceImp, ProdutoMapper produtoMapper) {
        this.documentSolrService = documentSolrService;
        this.produtoServiceImp = produtoServiceImp;
        this.produtoMapper = produtoMapper;
    }

    private ProdutoSolrDTO toDTO(Produto produto) {
        return new ProdutoSolrDTO(
                String.valueOf(produto.getId()),
                Collections.singletonList(produto.getNome()),
                Collections.singletonList(produto.getPreco())
        );
    }

    @Override
    public Produto salvar(Produto produto) {
        Produto produtoSave = produtoServiceImp.salvar(produto);
        ProdutoSolrDTO dto = toDTO(produtoSave);
        documentSolrService.salvar(dto);
        return produtoSave;
    }

    @Override
    public PageDTO<ProdutoResponseDTO> listar(Pageable pageable) {
        return  produtoServiceImp.listar(pageable);
    }

    public List<ProdutoResponseDTO> listar(String termo){
        return documentSolrService.buscar(termo)
                .stream()
                .map(produtoMapper::solrToDTO)
                .toList();
    }

    @Override
    public void deletar(Long id) {
        produtoServiceImp.deletar(id);
        documentSolrService.deletar(id);
    }

    @Override
    public Produto atualizar( Long id, Produto produto) {
        Produto atualizado = produtoServiceImp.atualizar( id ,produto);
        ProdutoSolrDTO dto = toDTO(atualizado);
        documentSolrService.atualizar(dto);
        return atualizado;
    }

    public Produto getProdutoById(Long id){
        return produtoServiceImp.produtoPorId(id);
    }
}
