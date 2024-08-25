package com.restaurante.api.domain.jpa;

import com.restaurante.api.domain.model.Cozinha;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CadastroCozinha {

    @PersistenceContext
    private EntityManager manager;

    List<Cozinha> listar (){
        TypedQuery<Cozinha> query = manager.createQuery("from Cozinha", Cozinha.class); //cria uma consulta pra cozinha
        return query.getResultList(); //retorna a lista de cozinhas
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha){
        return manager.merge(cozinha);
    }

    public Cozinha buscar(Long id){
        return manager.find(Cozinha.class, id);
    }
}
