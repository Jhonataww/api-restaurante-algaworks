package com.restaurante.api.service;
import com.restaurante.api.modelo.Cliente;
import com.restaurante.api.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AtivacaoClienteService {

    private List<Notificador> notificadores;

    public void ativar(Cliente cliente) {
        cliente.ativar();
        for (Notificador notificador : notificadores) {
            notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
        }
    }
}