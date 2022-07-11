package br.com.api.produtos.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.produtos.modelo.ProdutoModelo;
import br.com.api.produtos.servico.ProdutoServico;

@RestController // DEFINE A CLASSE COMO RESPONSÀVEL EM CRIAR ROTAS
public class ProdutoControle {
    
    @Autowired
    private ProdutoServico ps;

    @GetMapping("/rota") //TIPO DE REQUISIÇÃO GET ("tipo de rota")
    public String rota()
    {
        return "<h1>Essa rota está funcionando<h1>";
    }

    @GetMapping("/listar") //TIPO DE REQUISIÇÃO GET ("tipo de rota")
    public Iterable<ProdutoModelo> listar()
    {
        return ps.listar();
    }
}
