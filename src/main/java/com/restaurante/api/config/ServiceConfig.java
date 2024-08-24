package com.restaurante.api.config;

import com.restaurante.api.notificacao.Notificador;
import com.restaurante.api.service.AtivacaoClienteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public AtivacaoClienteService ativacaoClienteService(Notificador notificador) {
        return new AtivacaoClienteService(notificador); // Injeção de dependência ja gerenciado
    }
}
