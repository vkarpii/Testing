package com.webapp.database;

import java.util.ArrayList;

public abstract class AbstractDAO<E, K, V> {
    public abstract ArrayList<E> getAll();
    public abstract boolean update(E entity);
    public abstract E getEntityById(K id);

    public abstract E getEntityByString(V firstParam,V secondParam);
    public abstract boolean delete(K id);
    public abstract boolean create(E entity);
}
