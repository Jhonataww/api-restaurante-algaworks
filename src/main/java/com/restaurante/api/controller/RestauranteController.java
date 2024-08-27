package com.restaurante.api.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurante.api.domain.exception.EntidadeNaoEncontradaException;
import com.restaurante.api.domain.model.Restaurante;
import com.restaurante.api.domain.repository.RestauranteRepository;
import com.restaurante.api.domain.service.CadastroRestauranteService;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {


    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteRepository.listar();
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteRepository.buscar(restauranteId);

        if (restaurante != null) {
            return ResponseEntity.ok(restaurante);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try {
            restaurante = cadastroRestaurante.salvar(restaurante);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restaurante);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> atualizar(@PathVariable Long restauranteId,
                                       @RequestBody Restaurante restaurante) {
        try {
            Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);

            if (restauranteAtual != null) {
                BeanUtils.copyProperties(restaurante, restauranteAtual, "id");

                restauranteAtual = cadastroRestaurante.salvar(restauranteAtual);
                return ResponseEntity.ok(restauranteAtual);
            }

            return ResponseEntity.notFound().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PatchMapping("/{restaurante}")
    public ResponseEntity<?> atualizarParcial (@PathVariable Long restaurante, @RequestBody Map<String, Object> campos) {
        Restaurante restauranteAtual = restauranteRepository.buscar(restaurante);

        if (restauranteAtual == null) {
            return ResponseEntity.notFound().build();
        }

        merge(campos, restauranteAtual);

        return atualizar(restaurante, restauranteAtual);
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteAtual) {
        ObjectMapper objectMapper = new ObjectMapper();     // Cria um objeto ObjectMapper para converter o Map para um objeto Restaurante
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class); // Converte o Map para um objeto Restaurante

        dadosOrigem.forEach((nome, valor) -> {  //retorna instancia de um campo
            Field field = ReflectionUtils.findField(Restaurante.class, nome);   // Busca o campo na classe Restaurante usando reflection
            assert field != null;
            field.setAccessible(true);                                          // Torna o campo acessível -bypass
                                                //retorna valor do campo
            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem); // Pega o valor do campo no objeto Restaurante
                //seta valor do campo - precisa de uma instancia de campo um objeto e um valor
            ReflectionUtils.setField(field, restauranteAtual, novoValor);               // Seta o valor do campo na classe Restaurante
        });
    }

}