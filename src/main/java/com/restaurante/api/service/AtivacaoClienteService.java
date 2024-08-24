package com.restaurante.api.service;

import com.restaurante.api.config.TipoDoNotificador;
import com.restaurante.api.modelo.Cliente;
import com.restaurante.api.modelo.NivelUrgencia;
import com.restaurante.api.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    @TipoDoNotificador(NivelUrgencia.NORMAL)
    @Autowired
    private Notificador notificador;

    public void ativar(Cliente cliente) {
        cliente.ativar();
        notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
    }

}