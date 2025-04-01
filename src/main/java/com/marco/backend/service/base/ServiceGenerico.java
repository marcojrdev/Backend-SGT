package com.marco.backend.service.base;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.marco.backend.model.base.AbstractEntity;
import com.marco.backend.repository.base.GenericRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public abstract class ServiceGenerico<T extends AbstractEntity, ID extends Serializable>{

    public void delete(T entity) {
        entity.setAtivo(false);
        getRepository().save(entity);
    }

    public void deleteById(ID entityId) {
        Optional<T> op = getRepository().findById(entityId);
        if(op.isPresent()){
            T entity = op.get();
            entity.setAtivo(false);
            entity.setDataAtualizacao(LocalDateTime.now());
            getRepository().save(entity);
        }
    }

    public List<T> findAll() {
        return (List<T>) getRepository().findAll();
    }

    public Optional<T> findById(ID entityId) {
        return getRepository().findById(entityId);
    }

    public T save(T entity) {
        return (T) getRepository().save(entity);
    }

    public T update(T entity) {
        return getRepository().save(entity);
    }

    public T updateById(T entity, ID entityId) {
        Optional<T> op = getRepository().findById(entityId);
        if(op.isPresent()){
            T og = op.get();
            og.merge(entity);
            return (T) getRepository().save(og);
        }
        else
            return null;
    }
    
    public abstract GenericRepository<T,ID> getRepository();
    
}