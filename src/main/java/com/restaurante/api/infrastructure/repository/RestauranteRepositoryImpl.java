package com.restaurante.api.infrastructure.repository;

import com.restaurante.api.domain.model.Restaurante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {    //usar Impl no final do nome da classe para indicar que é uma implementação de uma interface

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> find(String nome,
                                  BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

        var jpql = new StringBuilder();
        jpql.append("from Restaurante where 0 = 0 "); //para não precisar de ifs para concatenar o where

        var parametros = new HashMap<String, Object>(); //será usado para passar os parâmetros para a query

        if (StringUtils.hasLength(nome)) {
            jpql.append("and nome like :nome "); //monta a query de acordo com os parâmetros passados
            parametros.put("nome", "%" + nome + "%");
        }

        if (taxaFreteInicial != null) {
            jpql.append("and taxaFrete >= :taxaInicial ");
            parametros.put("taxaInicial", taxaFreteInicial);
        }

        if (taxaFreteFinal != null) {
            jpql.append("and taxaFrete <= :taxaFinal ");
            parametros.put("taxaFinal", taxaFreteFinal);
        }

        TypedQuery<Restaurante> query = manager //typedQuery é uma query que retorna um tipo específico
                .createQuery(jpql.toString(), Restaurante.class);

        parametros.forEach((chave, valor) -> query.setParameter(chave, valor)); //lambda para passar os parâmetros para a query

        return query.getResultList();
    }

}
