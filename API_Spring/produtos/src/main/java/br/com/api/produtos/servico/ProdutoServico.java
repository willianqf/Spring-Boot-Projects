package br.com.api.produtos.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.produtos.modelo.ProdutoModelo;
import br.com.api.produtos.repositorio.ProdutoRepositorio;

@Service //INJEÇÃO DE DEPENDENCIA PARA CRIAR OBJETO E TER ACESSO A ESSES OBJETOS NESTA CLASSE 
public class ProdutoServico {
    
    @Autowired
    private ProdutoRepositorio pr;

    //Listar informações
    public Iterable<ProdutoModelo> listar()
    {
        return pr.findAll();
    }

}
