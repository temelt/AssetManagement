package com.vektorel.assetman.web.utilities;

import java.util.List;

import com.vektorel.assetman.web.utilities.ex.DbException;

public interface IDataService<T> {
    public T save(T entity) throws DbException;
    
    public T update(T entity) throws DbException;
        
    public boolean delete(T entity ) throws DbException;
    
    public List<T> getAll();
    
    public T getById(Long entityId);
}
