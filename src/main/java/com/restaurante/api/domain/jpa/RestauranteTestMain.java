package com.restaurante.api.domain.jpa;

import com.restaurante.api.RestauranteApplication;
import com.restaurante.api.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class RestauranteTestMain {

    public static void main(String[] args) {

        // SpringApplicationBuilder é uma classe que permite a configuração de uma aplicação Spring - ApplicationContext não retorna mais como no spring 2.0
        // WebApplicationType.NONE - não vai subir um servidor web
        ConfigurableApplicationContext configurableApplicationContext = new SpringApplicationBuilder(RestauranteApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepository restauranteRepository = configurableApplicationContext.getBean(RestauranteRepository.class);

        var restaurantes = restauranteRepository.listar();

        for (var restaurante : restaurantes) {
            System.out.printf("%s - %f - %s\n", restaurante.getNome(),
                    restaurante.getTaxaFrete(), restaurante.getCozinha().getNome());
        }

    }
}
