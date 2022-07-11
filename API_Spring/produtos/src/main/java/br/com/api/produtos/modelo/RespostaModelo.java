package br.com.api.produtos.modelo;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component // INJEÇÃO DE DEPENDÊNCIAS - Deixa para o spring instanciar o objeto
@Getter // LOMBOOK
@Setter
public class RespostaModelo {
    private String mensagem;
}
