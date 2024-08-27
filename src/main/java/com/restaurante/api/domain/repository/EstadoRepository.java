package com.restaurante.api.domain.repository;

import com.restaurante.api.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {

        List<Estado> listar();

        Estado buscar(Long id);

        Estado salvar(Estado estado);

        void remover(Long id);
}
