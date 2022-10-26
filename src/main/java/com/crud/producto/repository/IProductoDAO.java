package com.crud.producto.repository;

import com.crud.producto.entity.Producto;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IProductoDAO extends PagingAndSortingRepository<Producto, Integer> {
}
