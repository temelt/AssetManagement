package com.vektorel.assetman.web.utilities;

import java.util.List;

public interface IDataService<T> {
    public T save(T entity);
    
    public T update(T entity);
        
    public boolean delete(T entity);
    
    public List<T> getAll();
    
    public T getById(Long entityId);
}
