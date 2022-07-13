package br.com.projeto.api.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.api.modelo.Pessoa;



///CrudRepository - Permite alterar coisas do banco
///
///CrudRepository: Dispõe funcionalidades de CRUD (Create, Read, Update e Delete)
///PagingAndSortingRepository: Implementa métodos de paginação e dados sortidos
///JpaRepository: União entre o CrudRepository e o PagingAndSortingRepository
///
///<Modelo que será usado, Tipo de dado da sua chave primária>
/// @Repository -> DEFINE A CLASSE COMO REPOSITORY
@Repository
public interface Repositorio extends CrudRepository<Pessoa, Integer> {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Select * from 'tabela'
    // List -> Java.util
    // Select * from pessoas
    List<Pessoa> findAll(); // Quando executado o findAll será retornado um List com todos os elementos do banco

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Select * from pessoas where codigo=1 /////////////////////////////
    // Quando existe apenas 1 elemento
    Pessoa findByCodigo(int codigo);
    // Pessoa findByNome(String nome)
    // Para multiplos elementos
    // List<Pessoa> findByCodigo(int codigo);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ORDERNAR INFORMAÇÕES DA TABELA  Select * from tabela where 'coluna' = 'valor' ordern by 'coluna' //
    // List<Pessoa> findByOrderByNomeAsc(); Asc- menor para o maior
    // List<Pessoa> findByOrderByNomeDesc(); Desc- maior para o menor
    List<Pessoa> findByOrderByNome();
    List<Pessoa> findByOrderByIdade();
    List<Pessoa> findByNomeOrderByIdadeDesc(String nome);

    /////////////// FILTRAR ELEMENTOS 'LIKE %' //////////////////
    // TERMOS QUE CONTÉM O TERMO (Containing)
    List<Pessoa> findByNomeContaining(String termo);
    
    // TERMOS QUE INICIAM COM TAL INDICE (StartWith)
    List<Pessoa> findByNomeStartsWith(String termo);

    // TERMOS QUE FINALIZAM COM TAL INDICE (EndsWith)
    List<Pessoa> findByNomeEndsWith(String termo);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////// CRIANDO QUERRYs //////////////////////////////////
    // Querry(value="Comando SQL", nativeQuery = true)
    @Query(value = "SELECT SUM(idade) FROM PESSOAS", nativeQuery = true)
    int somaIdades();
    // Querry(value="Comando SQL :valores ", nativeQuery = true)
    // Usar " : " para passar parâmetros
    @Query(value = "SELECT * FROM pessoas WHERE idade >= :idade", nativeQuery = true)
    List<Pessoa> idadeMaior(int idade);
    //
    @Query(value = "SELECT * FROM pessoas WHERE idade = :idade", nativeQuery = true)
    List<Pessoa> idadeIgual(int idade);
    //
    @Query(value = "SELECT * FROM pessoas WHERE idade = :idade", nativeQuery = true)
    List<Pessoa> idadeIgualeNome(int idade, String nome);
    //
    ////////////////////////////////////////////////////////////
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // CONTADOR
    int countByCodigo(int codigo);
}
