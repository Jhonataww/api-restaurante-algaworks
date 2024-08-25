package com.restaurante.api.domain.repository;

import com.restaurante.api.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {

    List<Restaurante> listar();

    Restaurante buscar(Long id);

    Restaurante salvar(Restaurante cozinha);

    void remover(Restaurante cozinha);
}
