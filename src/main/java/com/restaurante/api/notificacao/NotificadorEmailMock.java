package com.restaurante.api.notificacao;

import com.restaurante.api.config.TipoDoNotificador;
import com.restaurante.api.modelo.Cliente;
import com.restaurante.api.modelo.NivelUrgencia;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@TipoDoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorEmailMock implements Notificador {
    public NotificadorEmailMock() {
        System.out.println("NotificadorEmail Mock");
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Mock Notificando %s através do e-mail %s: %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }
}