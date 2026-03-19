package com.example.sorl.dto;

import org.apache.solr.client.solrj.beans.Field;
import lombok.Data;

import java.util.List;


@Data
public class ProdutoSolrDTO {

    @Field("id")
    private String id;

    @Field("nome")
    private List<String> nome;

    @Field("preco")
    private List<Double> preco;

    public ProdutoSolrDTO() {}

    public ProdutoSolrDTO(String id, List<String> nome, List<Double> preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

//    public String getNomePrincipal() {
//        return (nome != null && !nome.isEmpty()) ? nome.get(0) : null;
//    }
//
//    public Double getPrecoPrincipal() {
//        return (preco != null && !preco.isEmpty()) ? preco.get(0) : null;
//    }
}