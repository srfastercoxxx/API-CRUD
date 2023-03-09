package com.crud.producto.helper.impl;

import com.crud.producto.entity.Producto;
import com.crud.producto.helper.IProductoHelper;
import com.crud.producto.viewmodel.ProductoModel;
import org.springframework.stereotype.Service;

@Service
public class ProductoHelper implements IProductoHelper {

    public Producto setProducto(ProductoModel request) {
        Producto in = new Producto();
        in.setCodigo(request.getCodigo());
        in.setNombre(request.getNombre());
        return in;
    }


}
