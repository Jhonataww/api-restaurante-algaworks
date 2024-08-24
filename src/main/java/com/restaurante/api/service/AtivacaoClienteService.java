package com.restaurante.api.service;

import com.restaurante.api.events.ClienteAtivoEvent;
import com.restaurante.api.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    @Autowired
    private ApplicationEventPublisher eventPublisher; //publicador de evento

    public void ativar(Cliente cliente) {
        cliente.ativar();
        eventPublisher.publishEvent(new ClienteAtivoEvent(cliente)); //publica o evento
    }

}