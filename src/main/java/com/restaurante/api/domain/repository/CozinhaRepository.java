package com.restaurante.api.domain.repository;

import com.restaurante.api.domain.model.Cozinha;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CozinhaRepository {

    List<Cozinha> listar();

    Cozinha buscar(Long id);

    Cozinha salvar(Cozinha cozinha);

    void remover(Long id);
}
