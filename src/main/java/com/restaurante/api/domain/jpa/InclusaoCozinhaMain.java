package com.restaurante.api.domain.jpa;

import com.restaurante.api.RestauranteApplication;
import com.restaurante.api.domain.model.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class InclusaoCozinhaMain {

    public static void main(String[] args) {

        // SpringApplicationBuilder é uma classe que permite a configuração de uma aplicação Spring - ApplicationContext não retorna mais como no spring 2.0
        // WebApplicationType.NONE - não vai subir um servidor web
        ConfigurableApplicationContext configurableApplicationContext = new SpringApplicationBuilder(RestauranteApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CadastroCozinha cadastroCozinha = configurableApplicationContext.getBean(CadastroCozinha.class);

        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Chinesa");

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Marroquina");

        System.out.printf("%d - %s\n", cozinha1.getId(), cozinha1.getNome());
        System.out.printf("%d - %s\n", cozinha2.getId(), cozinha2.getNome());

        cozinha1 = cadastroCozinha.adicionar(cozinha1);
        cozinha2 = cadastroCozinha.adicionar(cozinha2);

        System.out.printf("%d - %s\n", cozinha1.getId(), cozinha1.getNome());
        System.out.printf("%d - %s\n", cozinha2.getId(), cozinha2.getNome());

    }
}
