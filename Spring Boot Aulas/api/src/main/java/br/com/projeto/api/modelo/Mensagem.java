package br.com.projeto.api.modelo;

import org.springframework.stereotype.Component;

@Component //Informa para o Spring executar os elementos da classe Mensagem
public class Mensagem {
    
    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
