package br.com.api.produtos.controle;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.produtos.modelo.ProdutoModelo;
import br.com.api.produtos.servico.ProdutoServico;

@RestController // DEFINE A CLASSE COMO RESPONSÀVEL EM CRIAR ROTAS
public class ProdutoControle {
    
    @Autowired
    private ProdutoServico ps;

    @GetMapping("/tester")
    public String testador()
    {
        return "testando apenas";
    }
    @GetMapping("/rota") //TIPO DE REQUISIÇÃO GET ("tipo de rota")
    public String rota()
    {
        return "<h1>Essa rota está funcionando<h1>";
    }

    //Listar
    @GetMapping("/listar") //TIPO DE REQUISIÇÃO GET ("tipo de rota")
    public Iterable<ProdutoModelo> listar()
    {
        //Iterable -> Lista de dados
        return ps.listar();
    }

    //CADASTRAR
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody ProdutoModelo  pm)
    {
        return ps.CadastrarAlterar(pm, "cadastrar");
    }

    //Modificar
    @PutMapping("/modificar")
    public ResponseEntity<?> Modificar(@RequestBody ProdutoModelo  pm)
    {
        return ps.CadastrarAlterar(pm, "modificar");
    }

    //Deletar
    @DeleteMapping("/deletar/{codigo}")
    public ResponseEntity<?> Modificar(@PathVariable long codigo)
    {
        return ps.remover(codigo);
    }

}
