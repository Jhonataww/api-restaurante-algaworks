package com.restaurante.api.service;
import com.restaurante.api.modelo.Cliente;
import com.restaurante.api.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    private Notificador notificador;

    //pontos de injeção - não é muito recomendado usar - usar construtor
    @Autowired
    public AtivacaoClienteService(Notificador notificador) {
        this.notificador = notificador;
    }

//    public AtivacaoClienteService(String hostServidorSmtp) {
//    }

    public void ativar(Cliente cliente) {
        cliente.ativar();
        notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
    }

}