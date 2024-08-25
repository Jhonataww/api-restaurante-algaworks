package com.restaurante.api.domain.jpa;

import com.restaurante.api.RestauranteApplication;
import com.restaurante.api.domain.model.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class ExclusaoCozinhaMain {

    public static void main(String[] args) {

        // SpringApplicationBuilder é uma classe que permite a configuração de uma aplicação Spring - ApplicationContext não retorna mais como no spring 2.0
        // WebApplicationType.NONE - não vai subir um servidor web
        ConfigurableApplicationContext configurableApplicationContext = new SpringApplicationBuilder(RestauranteApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CadastroCozinha cadastroCozinha = configurableApplicationContext.getBean(CadastroCozinha.class);

        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);

        cadastroCozinha.remover(cozinha);
    }
}
