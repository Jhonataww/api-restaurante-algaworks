package com.restaurante.api.infrastructure.repository;

import com.restaurante.api.domain.model.Restaurante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {
    //usar Impl no final do nome da classe para indicar que é uma implementação de uma interface

    @PersistenceContext
    private EntityManager manager; //EntityManager: Interface que gerencia a comunicação entre a aplicação e o banco de dados.

    @Override
    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        //CriteriaBuilder: Utilizado para construir consultas dinâmicas.
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        //CriteriaQuery: Define a consulta a ser criada. No exemplo, criteria.from(Restaurante.class) define que a consulta é sobre a entidade Restaurante.
        CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
        Root<Restaurante> root = criteria.from(Restaurante.class);

        List<Predicate> predicates = new ArrayList<>();
        //predicateMap: Mapa que armazena os predicados (condições) da consulta.
        if(StringUtils.hasText(nome))
            predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
        if(taxaFreteInicial != null)
            predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
        if(taxaFreteFinal != null)
            predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));

        //Predicate: Representa uma condição da consulta. 'and' a cada vez que adiciona um predicado.
        criteria.where(predicates.toArray(new Predicate[0]));
        //toArray(new Predicate[0]): Converte a lista de predicados em um array de predicados.

        //TypedQuery: Cria a consulta tipada para a entidade Restaurante.
        TypedQuery<Restaurante> query = manager.createQuery(criteria);
        return query.getResultList();
        //query.getResultList(): Executa a consulta e retorna uma lista de resultados.
    }
}
