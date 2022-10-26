package com.crud.producto.service.impl;

import com.crud.producto.entity.Producto;
import com.crud.producto.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.crud.producto.repository.IProductoDAO;
import java.util.List;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private IProductoDAO productoDAO;

    @Override
    public List<Producto> findAll(){
        return (List<Producto>) productoDAO.findAll();
    }

    @Override
    public Page<Producto> findAll(Pageable pageRequest){
        return (Page<Producto>) productoDAO.findAll(pageRequest);
    }

    @Override
    public Producto save(Producto producto) {
        return productoDAO.save(producto);
    }

    @Override
    public Producto findById(int id) {
        return productoDAO.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        productoDAO.deleteById(id);
    }
}
