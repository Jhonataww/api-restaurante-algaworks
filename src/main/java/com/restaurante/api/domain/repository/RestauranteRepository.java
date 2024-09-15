package com.restaurante.api.domain.repository;

import com.restaurante.api.domain.model.Restaurante;
import com.restaurante.api.infrastructure.repository.RestauranteRepositoryQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);

    Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);

    Optional<Restaurante> findTop2ByNomeContaining(String nome);

    Integer countByCozinhaId(Long cozinhaId);

    //@Query("from Restaurante where nome like %:nome% and cozinha.id = :id") //query method jpql
    List<Restaurante> consultarPorNome(String nome, Long cozinhaId);

    //Spring sabe que existe uma implementação customizada para esse metodo
    List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
}
