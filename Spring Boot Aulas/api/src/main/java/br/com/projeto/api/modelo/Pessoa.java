package br.com.projeto.api.modelo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


////// TABELA PESSOA NO SQL //////
@Entity //Obriga a criar a tabela //
@Table(name = "pessoas") // @javax.persistence -> Permite nomear a tabela ()
public class Pessoa {
    //@Entity: Responsavel por criar tabela @javax.persistence
    //@Table(name = "nome desejável"): Alterar coisas específicas da tabela
    //@Id: Gera uma chave primária
    //@GeneratedValue(strategy = GenerationType.IDENTITY): implementa o auto incremento
    
    @Id //javax.persistence -> Define a chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // RESPONSAVEL POR GERAR VALORES DECRESCENTE 
    private int codigo; //Atributo como chave primaria
    
    private String nome;
   
    private int idade;

    //////////////////////////////////////////////////////////////////////////////////////////
    public int getCodigo() 
    {
        return codigo;
    }
    public void setCodigo(int codigo) 
    {
        this.codigo = codigo;
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////
    public int getIdade() 
    {
        return idade;
    }
    public void setIdade(int idade) 
    {
        this.idade = idade;
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    public String getNome() 
    {
        return nome;
    }
    public void setNome(String nome) 
    {
        this.nome = nome;
    }
    //////////////////////////////////////////////////////////////////////////////////////////
}
