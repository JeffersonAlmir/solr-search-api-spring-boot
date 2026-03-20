package com.example.sorl.service;

import com.example.sorl.dto.PageDTO;
import com.example.sorl.dto.ProdutoRequestDTO;
import com.example.sorl.dto.ProdutoResponseDTO;
import com.example.sorl.dto.ProdutoSolrDTO;
import com.example.sorl.entity.Produto;
import com.example.sorl.mapper.ProdutoMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private ProdutoSolrDTO toProdutoSolrDTO(ProdutoResponseDTO produtoResponse) {
        return new ProdutoSolrDTO(
                String.valueOf(produtoResponse.id()),
                Collections.singletonList(produtoResponse.nome()),
                Collections.singletonList(produtoResponse.preco())
        );
    }

    @Transactional
    @Override
    public ProdutoResponseDTO salvar(ProdutoRequestDTO dto) {
        ProdutoResponseDTO produtoSave = produtoServiceImp.salvar(dto);
        ProdutoSolrDTO solrDTO = toProdutoSolrDTO(produtoSave);
        documentSolrService.salvar(solrDTO);
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

    @Transactional
    @Override
    public void deletar(Long id) {
        produtoServiceImp.deletar(id);
        documentSolrService.deletar(id);
    }

    @Transactional
    @Override
    public ProdutoResponseDTO atualizar( Long id, ProdutoRequestDTO dto) {
        ProdutoResponseDTO atualizado = produtoServiceImp.atualizar( id ,dto);
        ProdutoSolrDTO solrDTO = toProdutoSolrDTO(atualizado);
        documentSolrService.atualizar(solrDTO);
        return atualizado;
    }

    public Produto getProdutoById(Long id){
        return produtoServiceImp.produtoPorId(id);
    }
}
