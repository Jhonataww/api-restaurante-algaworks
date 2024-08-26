package com.restaurante.api.controller;

import com.restaurante.api.domain.model.Cozinha;
import com.restaurante.api.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller //anotação de container spring
//@ResponseBody  //informa que a resposta do método será o corpo da resposta
@RestController //anotação que informa que a classe é um controlador rest (possui controller e responsebodyu)
@RequestMapping("/cozinhas") //mapeamento da requisição
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Cozinha> listar(){
       return cozinhaRepository.listar();
    }
}
