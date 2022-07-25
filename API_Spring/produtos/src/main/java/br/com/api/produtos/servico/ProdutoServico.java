package br.com.api.produtos.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.produtos.modelo.ProdutoModelo;
import br.com.api.produtos.modelo.RespostaModelo;
import br.com.api.produtos.repositorio.ProdutoRepositorio;


@Service //INJEÇÃO DE DEPENDENCIA PARA CRIAR OBJETO E TER ACESSO A ESSES OBJETOS NESTA CLASSE 
public class ProdutoServico {
    
    @Autowired
    private ProdutoRepositorio pr;

    @Autowired
    private RespostaModelo rm;

    // Listar informações
    public Iterable<ProdutoModelo> listar()
    {
        //Iterable -> Lista de elementos
        return pr.findAll();
    }

    // Metodo para cadastrar ou alterar produtos
    // Posso retornar tanto produtoModelo ou RespostaModelo
    public ResponseEntity<?> CadastrarAlterar(ProdutoModelo pm, String acao)
    {
        if(pm.getNome().equals(""))
        {
            rm.setMensagem("O nome do produto é obrigatórip");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        }
        else if(pm.getMarca().equals(""))
        {
            rm.setMensagem("O nome da marca é obrigatória");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        }
        else
        {
            if(acao.equals("cadastrar"))
            {
                return new ResponseEntity<ProdutoModelo>(pr.save(pm), HttpStatus.CREATED);
            }else
            {
                return new ResponseEntity<ProdutoModelo>(pr.save(pm), HttpStatus.OK);
                /* 
                Boolean find = false;
                Iterable<ProdutoModelo> produtos = listar();
                for(ProdutoModelo obj : produtos)
                {
                    if(obj.getCodigo() == pm.getCodigo())
                    {
                        find = true;
                    }
                }
                if(find)
                    return new ResponseEntity<ProdutoModelo>(pr.save(pm), HttpStatus.OK);
                else
                {
                    rm.setMensagem("Não foi encontrada o nome na base de dados");
                    return new ResponseEntity<RespostaModelo>(rm, HttpStatus.NOT_FOUND);

                }
                */
            }
            
        }
    }

    // METODO PARA REMOVER PRODUTOS
    public ResponseEntity<?> remover(long codigo)
    {
        Iterable<ProdutoModelo> produtos = listar();
        rm.setMensagem("Não foi encontrado o objeto que deseja deletar");
        ResponseEntity<RespostaModelo> msg = new ResponseEntity<RespostaModelo>(rm, HttpStatus.NOT_FOUND);
        for(ProdutoModelo obj : produtos)
        {
            if(obj.getCodigo() == codigo)
            {
                rm.setMensagem("Produto foi removido com sucesso");
                pr.deleteById(codigo);
                msg = new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);
                break;
            }
        }
        return msg;
    }

}
