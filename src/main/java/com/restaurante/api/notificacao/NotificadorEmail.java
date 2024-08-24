package com.restaurante.api.notificacao;

import com.restaurante.api.config.TipoDoNotificador;
import com.restaurante.api.modelo.Cliente;
import com.restaurante.api.modelo.NivelUrgencia;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("prod")
@TipoDoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorEmail implements Notificador {

    @Value("${notificador.email.host}")
    private String hostServidorSmtp;

    @Value("${notificador.email.port}")
    private String portaServidorSmtp;

    public NotificadorEmail() {
        System.out.println("NotificadorEmail REAL");
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("Host SMTP: " + this.hostServidorSmtp);
        System.out.printf("Notificando %s através do e-mail %s: %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }
}