package com.restaurante.api.config;

import com.restaurante.api.modelo.NivelUrgencia;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)  //informa tempo da anotação deve ser mantida a onde foi usada
@Qualifier
public @interface TipoDoNotificador {
    NivelUrgencia value();
}

