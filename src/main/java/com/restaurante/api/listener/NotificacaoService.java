package com.restaurante.api.listener;

import com.restaurante.api.config.TipoDoNotificador;
import com.restaurante.api.modelo.NivelUrgencia;
import com.restaurante.api.notificacao.Notificador;
import com.restaurante.api.events.ClienteAtivoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoService {
    @TipoDoNotificador(NivelUrgencia.NORMAL)
    @Autowired
    private Notificador notificador;

    @EventListener
    public void ClienteAtivoListener(ClienteAtivoEvent event) {
        System.out.printf("Cliente %s está ativo.\n", event.getCliente().getNome());
        notificador.notificar(event.getCliente(), "Seu cadastro no sistema está ativo!");
    }
}
