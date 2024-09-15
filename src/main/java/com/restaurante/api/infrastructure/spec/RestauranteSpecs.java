package com.restaurante.api.infrastructure.spec;

import com.restaurante.api.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestauranteSpecs {
    public static Specification<Restaurante> comFreteGratis() {
        return (root, query, builder) ->
                builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }

    public static Specification<Restaurante> comNomeSemelhante(String nome) {
        return (root, query, builder) ->
                builder.like(root.get("nome"), "%" + nome + "%");
    }

}


//No Spring Boot, ao usar a especificação (Specification) para construir consultas dinâmicas,
// a injeção de dependências e a configuração do framework desempenham um papel fundamental.
// Vou explicar como o CriteriaBuilder é retornado e como o Specification é integrado
// no contexto do Spring Data JPA.

//Entendendo a Specification no Spring Data JPA
//A interface Specification é uma parte do Spring Data JPA e é utilizada para construir consultas
// dinâmicas de maneira mais declarativa. Aqui está o que está acontecendo nos bastidores:
//
//Definição da Specification
//
//Specification Interface: Permite definir condições para consultas de maneira modular e reutilizável.
//
//Método toPredicate: Recebe três parâmetros:
//Root<Restaurante>: Representa a raiz da consulta, ou seja, a entidade sobre a qual a consulta está sendo feita.
//CriteriaQuery<?>: Representa a consulta que está sendo construída. Pode ser usado para definir características adicionais da consulta,
// como ordenação.
//CriteriaBuilder: Fornece métodos para construir expressões de critérios, como equal, like, greaterThan, etc.
//Como o CriteriaBuilder é Fornecido
//
//O CriteriaBuilder não é passado diretamente para você no seu código. Em vez disso,
// ele é fornecido pelo JPA através do contexto da consulta. Veja como isso funciona:
//
//Contexto da Consulta: Quando você usa uma Specification, o Spring Data JPA cria a consulta dinamicamente e utiliza
// o CriteriaBuilder para construir a consulta SQL correspondente.
//Injeção de Dependências e Contexto do JPA: O Spring Data JPA e o provedor JPA (como Hibernate)
// configuram e gerenciam o CriteriaBuilder em tempo de execução. Quando você cria uma Specification,
// o framework fornece o CriteriaBuilder ao método toPredicate quando a consulta é executada.
//
// Resumo
//O CriteriaBuilder é fornecido pelo framework JPA (como Hibernate) e é injetado de forma implícita quando você usa a Specification
// no Spring Data JPA. O framework se encarrega de criar a instância de CriteriaBuilder e passar para o método toPredicate da Specification
// quando a consulta é executada. Isso ocorre porque o CriteriaBuilder é parte do contexto da consulta JPA, que é gerenciado automaticamente
// pelo Spring e o provedor JPA.