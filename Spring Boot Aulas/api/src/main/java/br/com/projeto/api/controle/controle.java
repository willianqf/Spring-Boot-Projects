package br.com.projeto.api.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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


// CONTROLE -> MANIPULADOR DE ROTAS //

@RestController // Define a classe como controle de ROTASs
public class controle {

    //CRIANDO O OBJETO DO REPOSITORIO//
    @Autowired //Permite que o Spring implemente a instanciação do object sem a necessidade do operador NEW
    private Repositorio acao;

    @Autowired
    private Servico servico;
    /*
     * Criação de ROTAS
     * @(Get,Post,Put,Delete)Mapping("/rota/url/{variavel}")
     * public <Classe de retorno> rota1(@PathVariable String variavel)
     * {
     *      return "Olá, "+variavel
     * }
     */

    //ACÃO DE CADSTRAR PESSOA <METODO POST - GERAR JSON>
    //
    /* 
    @PostMapping("/api")
    public Pessoa cadastrar(@RequestBody Pessoa obj)
    {
        return acao.save(obj);
        SAVE PODE SALVAR COMO ALTERAR <Se a chave primária for igual ele altera>
    }
    */
    // USANDO SERVIÇOS //
    // @RequestBody -> Permite adicionar valores em formato de json no método POST
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
    public long contador() //Contador retornar um valor do tipo long
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

    @GetMapping("/api/idadeIgual/{valor}")
    public List<Pessoa> idadeIgual(@PathVariable int valor)
    {
        return acao.idadeIgual(valor);
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

    /////// GERAR BANCO ALEATORIO ////////////////////////////////////////////
    @GetMapping("/gerar")
    public void gerar()
    {
        int x = 0;
        while(x < 50)
        {
            Random random = new Random();
            String[] nome = {
                "Isadora", 
                "Laiz", 
                "Renato", 
                "Marcos", 
                "Roberto", 
                "Gilcaldu", 
                "Fulano", 
                "Beltrano"
            };
            String[] sobrenome = {
                "Fonseca",
                "Margoleri",
                "Sabour",
                "Narizta",
                "Fagound",
                "Donzeca",
                "Paula"
            };
            String[] terceironome = {
                "De Soares",
                "Augustin",
                "De la Fer",
                "Tamaz",
                "Munique",
                "Bernique"
            };
            int[] idade = {12, 51, 41, 32, 57, 13, 65, 15, 69, 51, 72, 74};
            int valorNome = random.nextInt(nome.length);
            int valorNome2 = random.nextInt(sobrenome.length);
            int valorNome3 = random.nextInt(terceironome.length);
            int valorIdade = random.nextInt(idade.length);
            Pessoa novapessoa = new Pessoa();
            String valor = nome[valorNome]+" "+sobrenome[valorNome2]+" "+terceironome[valorNome3];
            novapessoa.setNome(valor);
            novapessoa.setIdade(idade[valorIdade]);
            servico.cadastrar(novapessoa);
            x++;
        }
        //servico.cadastrar(novapessoa);

    }
///////////////////////////////////////////////////////////////////////////////////////////////////////
}
