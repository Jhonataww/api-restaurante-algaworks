package com.restaurante.api.domain.infrastructure.repository;

import com.restaurante.api.domain.model.Restaurante;
import com.restaurante.api.domain.repository.RestauranteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> listar(){
        TypedQuery<Restaurante> query = manager.createQuery("from Restaurante", Restaurante.class); //cria uma consulta pra cozinha
        return query.getResultList();
    }

    @Transactional
    @Override
    public Restaurante salvar(Restaurante restaurante){
        return manager.merge(restaurante);
    }

    @Override
    public Restaurante buscar(Long id){
        return manager.find(Restaurante.class, id);
    }

    @Transactional
    @Override
    public void remover(Restaurante restaurante){
        restaurante = buscar(restaurante.getId());
        manager.remove(restaurante);
    }
}
