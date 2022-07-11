package br.com.projeto.api.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;



///////// CRIANDO A VALIDAÇÃO DE DADOS VIA JPA ///////////
@Entity
@Table(name="clientes")
public class Cliente {
    @Id // DEFINE CHAVE PRIMÁRIA
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENTT (IDENTITY)
    private int codigo;
    
    @NotEmpty(message = "Informe um nome") // GARANTE QUE A ENTIDADE NÃO FIQUE VAZIA 
    private String nome;

    @Email(message = "Informe um e-mail válido") // VALIDA SE O EMAIL POSSUI "@" e "."
    private String email;

    ////////////////////////////////////////
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    ////////////////////////////////////////
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    ////////////////////////////////////////
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    ////////////////////////////////////////
}
