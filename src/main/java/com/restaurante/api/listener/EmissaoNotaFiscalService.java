package com.restaurante.api.listener;

import com.restaurante.api.events.ClienteAtivoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmissaoNotaFiscalService {

    @EventListener //Ouvinte de eventos
    public void clienteAtivoListener(ClienteAtivoEvent event) {
        System.out.println("Emitindo nota fiscal para " + event.getCliente().getNome());
    }
}
