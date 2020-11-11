package com.danh.service;

import java.util.List;

import com.danh.model.BaseModel;

public interface Services <T extends BaseModel>{
	T findById(int id);

    T findByName(String name);

    void insert(T model);

    void update(T model);

    void deleteById(int id);

    List<T> getAll();

    void deleteAll();

    public boolean isExist(T model);
}
