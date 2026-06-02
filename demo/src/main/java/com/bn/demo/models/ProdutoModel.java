package com.bn.demo.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
// fala pro spring que essa classe representa uma tabela no banco de dados
@Table(name = "TB_PRODUTO")
// define o nome da tabela lá no banco, no caso vai se chamar TB_PRODUTO
public class ProdutoModel {

    @Id
    // marca esse campo como a chave primária da tabela, o famoso ID único de cada registro
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // faz o banco gerar o id sozinho e automaticamente, você não precisa passar o id na mão
    private Long id;
    private String nome;
    @Column(name = "preco_do_produto")
    // renomeia a coluna no banco, em vez de salvar como "preco" vai salvar como "preco_do_produto"
    private BigDecimal preco;
    @Column(name = "quant_estoque")
    // mesma coisa, a coluna no banco vai se chamar "quant_estoque" em vez de só "estoque"
    private Integer estoque;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPreco() { return preco; }

    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public Integer getEstoque() { return estoque; }

    public void setEstoque(Integer estoque) { this.estoque = estoque; }
}