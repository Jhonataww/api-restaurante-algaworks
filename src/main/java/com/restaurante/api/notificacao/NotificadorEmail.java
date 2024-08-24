package com.restaurante.api.notificacao;

import com.restaurante.api.config.TipoDoNotificador;
import com.restaurante.api.modelo.Cliente;
import com.restaurante.api.modelo.NivelUrgencia;
import org.springframework.stereotype.Component;

@TipoDoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorEmail implements Notificador {
    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s através do e-mail %s: %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }
}