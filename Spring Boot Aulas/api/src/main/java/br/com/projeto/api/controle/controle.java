package br.com.projeto.api.controle;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.modelo.Cliente;
import br.com.projeto.api.modelo.Pessoa;
import br.com.projeto.api.repositorio.Repositorio;
import br.com.projeto.api.servico.Servico;


// MANIPULADOR DE ROTAS //

@RestController
public class controle {

    //CRIANDO O OBJETO REPOSITORIO//
    @Autowired //Permite que o Spring implemente a instanciação do object sem a necessidade do operador NEW
    private Repositorio acao;

    @Autowired
    private Servico servico;

    //ACÃO DE CADSTRAR PESSOA <METODO POST - GERAR JSON>
    //
    /* 
    @PostMapping("/api")
    public Pessoa cadastrar(@RequestBody Pessoa obj)
    {
        return acao.save(obj);
    }
    */
    // USANDO SERVIÇOS //
    @PostMapping("/api")
    public ResponseEntity<?> cadastrar(@RequestBody Pessoa obj)
    {
        return servico.cadastrar(obj);
    }

    // USANDO SELECT DO BANCO ///
    // SELECIONAR TODOS DO BANCO 
    //List<Pessoa> findAll();
    /*
    @GetMapping("/api")
    public List<Pessoa> selecionar()
    {
        return acao.findAll();
    }
    */
    /* USANDO ResponseEntity */
    @GetMapping("/api")
    public ResponseEntity<?> selecionar()
    {
        return servico.selecionar();
    }


    // SELECIONAR DADO ESPECÍFICO
    // Pessoa findBy'coluna'();
    /*
    @GetMapping("/api/{codigo}")
    public Pessoa SelecionarCodigo(@PathVariable int codigo)
    {
        return acao.findByCodigo(codigo);
    }
    */
    //////// USANDO ResponsiveEntity ////////

    @GetMapping("/api/{codigo}")
    public ResponseEntity<?> SelecionarCodigo(@PathVariable int codigo)
    {
        return servico.selecionarPeloCodigo(codigo);
    }

    ///////////////////////////////////////////////////////////
    // USANDO ALTER TABLE ///
    @PutMapping("/api") //Requisição: PutMapping() <- precisa passar objeto completo
    public ResponseEntity<?> editar(@RequestBody Pessoa obj)
    {
        return servico.editar(obj);
    }
    //////////////////////////////////////////////////////////
    // USANDO DELETE //
    @DeleteMapping("/api/{codigo}")
    public ResponseEntity<?> remover(@PathVariable int codigo)
    {
        /* 
        Pessoa obj = SelecionarCodigo(codigo);
        acao.delete(obj);
        */
        return servico.removerPessoa(codigo);
    }
    // DEFINE A ROTA QUE SERÁ CHAMADA ///
    // @GetMapping("/rota/rota2") Define rota ///
    @GetMapping("/rota1")
    public String mensagem()
    {
        return "<h1>Hellow Word<h1>";
    }

    ///////// PASSANDO VARIÁVEIS /////////////
    @GetMapping("/rota2/{nome}")
    public String boasVindas(@PathVariable String nome)
    {
        return "<h1>Seja Bem-vindo, "+nome+"<h1>";
    }

    @GetMapping("/rota2")
    public String boasVindas()
    {
        return "<h1>Seja Bem-vindo Anonimo<h1>";
    }

    ///////////PASSANDO OS DADOS POR REQUEST///////////////////
    @PostMapping("/pessoa") //
    public Pessoa person(@RequestBody Pessoa p)
    {
        return p;
    }

    ///////// USANDO COUNT ////////////////////////////////////
    //// PERMITE CONTAR A QUANTIDADE DE PESSOAS CADASTRADAS ///
    @GetMapping("/api/contador")
    public long contador()
    {
        return acao.count();
    }

    /////// ORDERNANDO A TABELA ///////////////////

    @GetMapping("/api/ordernarNomes")
    public List<Pessoa> ordenarNomes()
    {
        return acao.findByOrderByNome();
    }

    @GetMapping("/api/ordernarIdades")
    public List<Pessoa> ordenarIdades()
    {
        return acao.findByOrderByIdade();
    }

    @GetMapping("/api/nome/{nome}")
    public List<Pessoa> ordernarNomes2(@PathVariable String nome)
    {
        return acao.findByNomeOrderByIdadeDesc(nome);
    }

    ///////// VERFICAR SE EXISTE TERMO BANCO  % LIKE % ////////////////
    @GetMapping("/api/termo/{termo}")
    public List<Pessoa> verificarTermo(@PathVariable String termo)
    {
        return acao.findByNomeContaining(termo);
    }

    @GetMapping("/api/inicia/{value}")
    public List<Pessoa> pessoaIniciaCom(@PathVariable String value)
    {
        return acao.findByNomeStartsWith(value);
    }

    @GetMapping("/api/finaliza/{value}")
    public List<Pessoa> pessoaFinalizaCom(@PathVariable String value)
    {
        return acao.findByNomeEndsWith(value);
    }
    ////////////////////////////////////////////////////////////////////
    ////////////////// EXECUTAR QUERRYS CRIADAS ////////////////////////
    @GetMapping("/api/somarIdades")
    public int somadaIdades()
    {
        return acao.somaIdades();
    }
    
    @GetMapping("/api/verificarIdade/{valor}")
    public List<Pessoa> idadeMaior(@PathVariable int valor)
    {
        return acao.idadeMaior(valor);
    }

    ////////////////////// ResponseEntity ////////////////////////////////
    /// CRIANDO STATUS DE RESPOSTAS -> ResponseEntity<?> (new ResponseEntity<>(HttpStatus.))
    @GetMapping("/status")
    public ResponseEntity<?> status()
    {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    ///////////SERVIÇOS <Local onde fica as regras de negocio//////////////

    //////////////////// CLIENTE (VÁLIDO) ///////////////////////////////
    @PostMapping("/cliente")
    public void cliente(@Valid @RequestBody Cliente obj) //@Valid -> Valida (@NotEmpty e @Email)
    {
        
    }

}
