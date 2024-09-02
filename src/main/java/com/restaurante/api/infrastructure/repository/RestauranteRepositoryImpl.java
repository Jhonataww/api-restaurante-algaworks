package com.restaurante.api.infrastructure.repository;

import com.restaurante.api.domain.model.Restaurante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {    //usar Impl no final do nome da classe para indicar que é uma implementação de uma interface


    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

            var jpql = "from Restaurante where nome like :nome and taxaFrete between :taxaInicial and :taxaFinal";

            return manager.createQuery(jpql, Restaurante.class)
                    .setParameter("nome", "%" + nome + "%")
                    .setParameter("taxaInicial", taxaFreteInicial)
                    .setParameter("taxaFinal", taxaFreteFinal)
                    .getResultList();
    }
}
