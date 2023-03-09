package com.crud.producto.controller;


import com.crud.producto.entity.Producto;
import com.crud.producto.util.MarkdownDescriptionFile;
import com.crud.producto.viewmodel.ErrorGenerico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;


@Validated
public interface ProductAPI {


    @MarkdownDescriptionFile("producto-controller-listado.md")
    @Operation(summary = "Obtener Producto", description = "", tags = {"producto-controller",})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(responseCode = "400", description = "error 400", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(responseCode = "403", description = "error Forbidden", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(responseCode = "404", description = "error 404", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(responseCode = "500", description = "error 500", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))) })
    @RequestMapping(value = "/productos",
            produces = {"application/json;charset=UTF-8"},
            method = RequestMethod.GET)
    List<Producto> index();


    @MarkdownDescriptionFile("producto-controller-listado.md")
    @Operation(summary = "Obtener Producto", description = "", tags = {"producto-controller",})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(responseCode = "400", description = "error 400", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(responseCode = "403", description = "error Forbidden", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(responseCode = "404", description = "error 404", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(responseCode = "500", description = "error 500", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))) })
    @RequestMapping(value = "/productos-paginated",
            produces = {"application/json;charset=UTF-8"},
            method = RequestMethod.GET)
    ResponseEntity<Page<Producto>> getProductsPaginated(@RequestParam(value = "page", required = true, defaultValue="0") Integer page, @RequestParam(value = "size", required = true, defaultValue="10") Integer size);


    @MarkdownDescriptionFile("producto-controller-obtener.md")
    @Operation(summary = "Obtener Producto", description = "", tags = {"producto-controller",})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(responseCode = "400", description = "error 400", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(responseCode = "403", description = "error Forbidden", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(responseCode = "404", description = "error 404", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(responseCode = "500", description = "error 500", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))) })
    @RequestMapping(value = "/producto/{id}",
            produces = {"application/json;charset=UTF-8"},
            method = RequestMethod.GET)
    ResponseEntity<?> show(@PathVariable @Min(1) int id);


    @MarkdownDescriptionFile("producto-controller-guardar.md")
    @Operation(summary = "Guarda Producto", description = "", tags = {"producto-controller",})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(responseCode = "400", description = "error 400", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(responseCode = "403", description = "error Forbidden", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(responseCode = "404", description = "error 404", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(responseCode = "500", description = "error 500", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))) })
    @RequestMapping(value = "/producto",
            produces = {"application/json;charset=UTF-8"},
            consumes = {"application/json;charset=UTF-8"},
            method = RequestMethod.POST)
    ResponseEntity<?> create(@Valid @RequestBody Producto product);


    @MarkdownDescriptionFile("producto-controller-editar.md")
    @Operation(summary = "Edita Producto", description = "", tags = {"producto-controller",})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(responseCode = "400", description = "error 400", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(responseCode = "403", description = "error Forbidden", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(responseCode = "404", description = "error 404", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(responseCode = "500", description = "error 500", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))) })
    @RequestMapping(value = "/producto/{id}",
            produces = {"application/json;charset=UTF-8"},
            consumes = {"application/json;charset=UTF-8"},
            method = RequestMethod.PUT)
    ResponseEntity<?> update(@RequestBody @Valid Producto product, @PathVariable @Min(1) int id);


    @MarkdownDescriptionFile("producto-controller-eliminar.md")
    @Operation(summary = "Eliminar Producto", description = "", tags = {"producto-controller",})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(responseCode = "400", description = "error 400", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(responseCode = "403", description = "error Forbidden", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(responseCode = "404", description = "error 404", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))),
            @ApiResponse(responseCode = "500", description = "error 500", content = @Content(schema = @Schema(implementation = ErrorGenerico.class))) })
    @RequestMapping(value = "/producto/{id}",
            produces = {"application/json;charset=UTF-8"},
            method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable @Min(1) int id);
}
