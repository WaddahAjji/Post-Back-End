package com.post.base;

import jakarta.persistence.Cacheable;
import jakarta.persistence.MappedSuperclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;
import java.util.Optional;

@MappedSuperclass
public class BaseService<T extends  BaseEntity<ID>,ID extends Number> {


    @Autowired
    private BaseRepository<T,ID>  baseRepository;

    public Optional<T> findById(ID id){
        return  baseRepository.findById(id);
    }


    public List<T> findAll(){
        return  baseRepository.findAll();
    }
    public  T insert(T entity){
        if(entity.getId()!=null)
        {
            throw new RuntimeException();
        }
        return   baseRepository.save(entity);
    }

    public  List<T> insert(List<T> entity){

        return   baseRepository.saveAll(entity);
    }

    public void delete(ID id) {
        T entity= baseRepository.findById(id).orElseThrow();
        baseRepository.delete(entity);
    }


    public T update(T entity) {

        return baseRepository.save(entity);
    }
}
