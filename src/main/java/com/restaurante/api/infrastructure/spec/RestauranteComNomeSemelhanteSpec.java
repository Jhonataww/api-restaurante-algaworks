package com.restaurante.api.infrastructure.spec;

import com.restaurante.api.domain.model.Restaurante;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestauranteComNomeSemelhanteSpec implements Specification<Restaurante> {

    private String nome;

    public RestauranteComNomeSemelhanteSpec(String nome) {
        this.nome = nome;
    }
    @Override
    public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
       return criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
    }
}
