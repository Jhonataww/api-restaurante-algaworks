package com.restaurante.api.notificacao;

import com.restaurante.api.config.NotificadorProperties;
import com.restaurante.api.config.TipoDoNotificador;
import com.restaurante.api.modelo.Cliente;
import com.restaurante.api.modelo.NivelUrgencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("prod")
@TipoDoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorEmail implements Notificador {

   @Autowired
   private NotificadorProperties properties;

    public NotificadorEmail() {
        System.out.println("NotificadorEmail REAL");
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("Host SMTP: " + this.properties.getHost());
        System.out.printf("Notificando %s através do e-mail %s: %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }
}