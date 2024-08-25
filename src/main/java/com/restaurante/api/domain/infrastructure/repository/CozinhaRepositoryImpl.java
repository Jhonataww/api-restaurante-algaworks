package com.restaurante.api.domain.infrastructure.repository;

import com.restaurante.api.domain.model.Cozinha;
import com.restaurante.api.domain.repository.CozinhaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component  //isso vai virar repositorio
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cozinha> listar(){
        TypedQuery<Cozinha> query = manager.createQuery("from Cozinha", Cozinha.class); //cria uma consulta pra cozinha
        return query.getResultList();
    }

    @Transactional
    @Override
    public Cozinha salvar(Cozinha cozinha){
        return manager.merge(cozinha);
    }

    @Override
    public Cozinha buscar(Long id){
        return manager.find(Cozinha.class, id);
    }

    @Transactional
    @Override
    public void remover(Cozinha cozinha){
        cozinha = buscar(cozinha.getId());
        manager.remove(cozinha);
    }
}
