package com.crud.producto.controller;


import com.crud.producto.entity.Producto;
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
import org.springframework.web.bind.annotation.*;
import com.crud.producto.service.IProductoService;
import com.crud.producto.util.SortedUnpaged;


import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@Validated
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private IProductoService productoService;

    @GetMapping("/productos")
    public List<Producto> index() {
        return productoService.findAll();
    }

    @GetMapping("/productos-paginated")
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

    @GetMapping("/producto/{id}")
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

    @PostMapping("/producto")
    public ResponseEntity<?> create(@Valid @RequestBody Producto producto) {
        Producto newProducto = null;

        Map<String, Object> response = new HashMap<>();
        try {
            newProducto = productoService.save(producto);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la insercion en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El registro ha sido creado con exito");
        response.put("producto", newProducto);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/producto/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid Producto producto, @PathVariable int id) {
        Producto currentProducto = productoService.findById(id);
        Producto productoUpdated = null;
        Map<String, Object> response = new HashMap<>();
        if (currentProducto == null) {
            response.put("mensaje", "El registro con ID: "+id+" no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            currentProducto.setCodigo(producto.getCodigo());
            currentProducto.setNombre(producto.getNombre());
            productoUpdated = productoService.save(currentProducto);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el registro en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El registro ha sido actualizado con exito");
        response.put("producto", productoUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/producto/{id}")
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
