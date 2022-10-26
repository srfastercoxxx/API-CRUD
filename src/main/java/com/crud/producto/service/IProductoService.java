package com.crud.producto.service;

import com.crud.producto.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductoService {

    public List<Producto> findAll();

    public Page<Producto> findAll(Pageable pageRequest);

    public Producto save(Producto producto);

    public Producto findById(int id);

    public void delete(int id);
}
