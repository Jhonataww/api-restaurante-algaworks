package com.restaurante.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.restaurante.api.domain.model.Cozinha;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@JacksonXmlRootElement(localName = "cozinhas") //nome do elemento raiz
@Data //anotação do lombok para gerar os métodos getters, setters, equals, hashcode e toString
public class CozinhasXmlWrapper {

    @JsonProperty("cozinha") //nome do campo na serialização
    @JacksonXmlElementWrapper(useWrapping = false) //informa que o elemento não deve ser encapsulado
    @NonNull // anotação do lombok para gerar um construtor com todos os argumentos obrigatórios
    private List<Cozinha> cozinhas;


}
