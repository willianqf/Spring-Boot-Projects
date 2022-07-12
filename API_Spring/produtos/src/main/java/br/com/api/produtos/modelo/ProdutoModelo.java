package br.com.api.produtos.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity // ESPECÍFICA PARA CRIAR A TABELA
@Table(name="produtos") //ESPECÍFICA O NOME DA TABELA
@Getter //Geração de Get e Set do LOMBOK
@Setter //
public class ProdutoModelo {
    @Id // ESPECÍFICA QUE É CHAVE PRIMÁRIA
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DEFINE GERAÇÃO AUTOMÁTICA
    private Long codigo;
    /////////////////////////////////////////////////////
    private String nome;
    ////////////////////////////////////////////////////
    private String marca;
}
