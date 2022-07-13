package br.com.projeto.api.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import br.com.projeto.api.modelo.Mensagem;
import br.com.projeto.api.modelo.Pessoa;
import br.com.projeto.api.repositorio.Repositorio;

@Service // DEFINIR QUE ESTA É UMA CLASSE DE SERVIÇOS 
public class Servico {

    @Autowired // Obriga a instanciação do objeto pelo spring
    private Mensagem mensagem;

    @Autowired
    private Repositorio acao;

    // METODO PARA CADASTRAR PESSOAS
    public ResponseEntity<?> cadastrar(Pessoa obj)
    {
    
        if(obj.getNome().equals(""))
        {
            mensagem.setMensagem("O nome precisa ser preenchido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        else if(obj.getIdade() < 0 || obj.getIdade() > 100)
        {
            mensagem.setMensagem("Idade não preenchida ou Inválida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else
        {
            return new ResponseEntity<>(acao.save(obj), HttpStatus.CREATED);

        }
    }

    // METODO PARA SELECIONAR PESSOAS
    public ResponseEntity<?> selecionar()
    {
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }

    // METODO PARA SELECIONAR PESSOAS PELO CÓDIGO
    public ResponseEntity<?> selecionarPeloCodigo(int codigo)
    {
        if(acao.countByCodigo(codigo) == 0)
        {
            mensagem.setMensagem("Não encontramos nenhuma pessoa");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        else
        {
            return new ResponseEntity<>(acao.findByCodigo(codigo), HttpStatus.OK);
        }
    }

    // METODO PARA EDITAR PESSOAS
    public ResponseEntity<?> editar(Pessoa obj)
    {
        if(acao.countByCodigo(obj.getCodigo()) == 0)
        {
            mensagem.setMensagem("O código informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        else if(obj.getNome().equals(""))
        {
            mensagem.setMensagem("É necessário informar um nome");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        else if(obj.getIdade() < 0)
        {
            mensagem.setMensagem("É necessário informar uma idade válida");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        else
        {
            return new ResponseEntity<>(acao.save(obj), HttpStatus.OK); 
        }
    }

    // METODO PARA REMOVER A PESSOA PELO CODIGO

    public ResponseEntity<?> removerPessoa(int codigo)
    {
        if(selecionarPeloCodigo(codigo).getStatusCode().equals(HttpStatus.OK))
        {
            Pessoa pes = acao.findByCodigo(codigo);
            acao.delete(pes);
            mensagem.setMensagem("Deletado com sucesso");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
        else
        {
            mensagem.setMensagem("Pessoa não existe para ser deletada");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
    }


}
