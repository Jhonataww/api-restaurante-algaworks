package com.restaurante.api.config;

import com.restaurante.api.notificacao.NotificadorEmail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificacaoConfig {

    // Classe de configuração e bean de configuração - informo ao Spring que quero gerenciar a criação de um objeto
    @Bean
    public NotificadorEmail notificadorEmail() {
        NotificadorEmail notificador = new NotificadorEmail("smtp.restaurante.com.br");
        notificador.setCaixaAlta(true);
        return notificador;
    }
}
