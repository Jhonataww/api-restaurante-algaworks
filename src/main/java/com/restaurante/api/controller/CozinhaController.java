package com.restaurante.api.controller;

import com.restaurante.api.domain.model.Cozinha;
import com.restaurante.api.domain.repository.CozinhaRepository;
import com.restaurante.api.model.CozinhasXmlWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller //anotação de container spring
//@ResponseBody  //informa que a resposta do método será o corpo da resposta
@RestController //anotação que informa que a classe é um controlador rest (possui controller e responsebodyu)
//@RequestMapping(value = "/cozinhas", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}) // Geral no recurso - mapeamento da requisição e especificando o produces para que o método possa retornar tanto JSON quanto XML
@RequestMapping("/cozinhas") //mapeamento da requisição
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cozinha> listar(){
       return cozinhaRepository.listar();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml(){
        return new CozinhasXmlWrapper(cozinhaRepository.listar());
    }

    @GetMapping("/{cozinhaId}") //path variable
    public Cozinha buscar(@PathVariable("cozinhaId") Long id){ //bind com parametro do metodo
        return cozinhaRepository.buscar(id);
    }
}
