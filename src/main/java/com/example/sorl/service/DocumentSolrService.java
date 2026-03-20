package com.example.sorl.service;

import com.example.sorl.dto.ProdutoSolrDTO;
import com.example.sorl.exceptions.ProdutoSolrException;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class DocumentSolrService  {

    private final SolrClient solrClient;

    public DocumentSolrService ( SolrClient solrClient){

        this.solrClient = solrClient;
    }

    @Retryable(
            retryFor = ProdutoSolrException.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000)
    )
    public ProdutoSolrDTO salvar(ProdutoSolrDTO produto)  {

        try {
            solrClient.addBean( produto);
            solrClient.commit();

            log.info("Produto salvo no Solr;");

            return produto;
        } catch (SolrServerException | IOException e) {
            log.error("Erro ao salvar produto no Solr", e);
            throw new ProdutoSolrException("Erro ao salvar produto no Solr", e);
        }
    }

    @Recover
    public ProdutoSolrDTO recoverSalvar(ProdutoSolrException e, ProdutoSolrDTO produtoSolrDTO){
        log.warn("Falha nas tentativas de salvar no Solr ID: {}",produtoSolrDTO.getId());
        return produtoSolrDTO;
    }


    public List<ProdutoSolrDTO> buscar(String termo)  {
        try {
            String termoEscapado = ClientUtils.escapeQueryChars(termo);
            SolrQuery query = new SolrQuery();
            query.set("defType","edismax");
            query.set("q", termoEscapado + "~2");
            query.set("qf","nome");
            query.set("pf", "nome^5");
            query.setRows(5);
            QueryResponse response = solrClient.query(query);

            return response.getBeans(ProdutoSolrDTO.class);
        } catch (Exception e) {
            log.error("Erro ao consultar produtos no Solr", e);
            throw new ProdutoSolrException("Erro ao consultar produtos no Solr", e);
        }
    }

    @Retryable(
            retryFor = ProdutoSolrException.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000)
    )
    public void deletar(Long id)  {
        try {
            solrClient.deleteById(String.valueOf(id));
            solrClient.commit();
        } catch (SolrServerException | IOException e) {
            log.error("Erro ao deletar produto no Solr", e);
            throw new ProdutoSolrException("Erro ao deletar produto no Solr", e);
        }
    }

    @Recover
    public void recoverDelete(ProdutoSolrException e, Long id){
        log.warn("Falha nas tentativas de deletar objeto no Solr ID: {}",id);
    }

    public ProdutoSolrDTO atualizar(ProdutoSolrDTO produto) {
        return salvar(produto);
    }
}
