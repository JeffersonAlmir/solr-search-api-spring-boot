package com.example.sorl.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="tb_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Double preco;

    public Produto(Long id, String nome, Double preco) {
        this.id = id;
        this.preco = preco;
        this.nome = nome;
    }

    public Produto() {}


}
