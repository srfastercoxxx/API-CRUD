package com.crud.producto.controller;


import com.crud.producto.entity.Producto;
import com.crud.producto.helper.IProductoHelper;
import com.crud.producto.service.IProductoService;
import com.crud.producto.util.SortedUnpaged;
import com.crud.producto.viewmodel.ProductoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Validated
@Controller
@RequestMapping("/api/v1")
public class ProductController implements ProductAPI {

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IProductoHelper productoHelper;

    @Override
    public ResponseEntity<List<Producto>> index() {
        ResponseEntity<List<Producto>> pageResponseEntity = new ResponseEntity<>(productoService.findAll(), HttpStatus.OK);
        return pageResponseEntity;
    }

    @Override
    public ResponseEntity<Page<Producto>> getProductsPaginated(@RequestParam(value = "page", required = true, defaultValue="0") Integer page, @RequestParam(value = "size", required = true, defaultValue="10") Integer size) {
        Sort sort =  Sort.by("id").descending();
        Pageable pageRequest;
        if (size > 0) {
            pageRequest = PageRequest.of(page, size, sort);
        }
        else {
            pageRequest = SortedUnpaged.getInstance(sort);
        }
        Page<Producto> pageResult = productoService.findAll(pageRequest);
        ResponseEntity<Page<Producto>> pageResponseEntity = new ResponseEntity<>(pageResult, HttpStatus.OK);
        return pageResponseEntity;
    }

    @Override
    public ResponseEntity<?> show(@PathVariable @Min(1) int id) {
        Producto producto = null;
        Map<String, Object> response = new HashMap<>();
        try{
            producto = productoService.findById(id);
        } catch(DataAccessException e){
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(producto == null){
            response.put("mensaje", "El registro con ID: "+id+" no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Producto>(producto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> create(@Valid @RequestBody ProductoModel producto) {
        Producto newProducto = new Producto();
        Map<String, Object> response = new HashMap<>();
        try {
            newProducto = productoService.save(productoHelper.setProducto(producto));
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la insercion en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El registro ha sido creado con exito");
        response.put("producto", newProducto);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> update(@RequestBody @Valid ProductoModel producto, @PathVariable @Min(1) int id) {
        Producto currentProducto = productoService.findById(id);
        Producto productoUpdated = null;
        Map<String, Object> response = new HashMap<>();
        if (currentProducto == null) {
            response.put("mensaje", "El registro con ID: "+id+" no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            productoUpdated = productoService.save(productoHelper.setProducto(producto));
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el registro en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El registro ha sido actualizado con exito");
        response.put("producto", productoUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> delete(@PathVariable @Min(1) int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            productoService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el registro en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El registro ha sido eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
