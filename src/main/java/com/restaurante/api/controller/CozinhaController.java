package com.restaurante.api.controller;

import com.restaurante.api.domain.exception.EntidadeEmUsoException;
import com.restaurante.api.domain.exception.EntidadeNaoEncontradaException;
import com.restaurante.api.domain.model.Cozinha;
import com.restaurante.api.domain.repository.CozinhaRepository;
import com.restaurante.api.domain.service.CadastroCozinhaService;
import com.restaurante.api.model.CozinhasXmlWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@Controller //anotação de container spring
//@ResponseBody  //informa que a resposta do método será o corpo da resposta
@RestController //anotação que informa que a classe é um controlador rest (possui controller e responsebodyu)
//@RequestMapping(value = "/cozinhas", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}) // Geral no recurso - mapeamento da requisição e especificando o produces para que o método possa retornar tanto JSON quanto XML
@RequestMapping("/cozinhas") //mapeamento da requisição
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    CadastroCozinhaService cadastroCozinha;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cozinha> listar(){
       return cozinhaRepository.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml(){
        return new CozinhasXmlWrapper(cozinhaRepository.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //status 201
    public Cozinha adicionar(@RequestBody Cozinha cozinha){
      return cadastroCozinha.salvar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha){
        Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(cozinhaId);

        if(cozinhaAtual.isPresent()){
            BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
            return ResponseEntity.ok(cadastroCozinha.salvar(cozinhaAtual.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId){
        try {
            cadastroCozinha.excluir(cozinhaId);
            return ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @ResponseStatus(HttpStatus.OK) //status 201
    @GetMapping("/{cozinhaId}") //path variable
    public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long id){ //bind com parametro do metodo
        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);

        return cozinha.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
