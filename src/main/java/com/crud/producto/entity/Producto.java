package com.crud.producto.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "codigo no debe ser vacio")
    @NotNull(message = "codigo no debe ser nulo")
    @Size(max = 20)
    private String codigo;

    @NotBlank(message = "nombre no debe ser vacio")
    @NotNull(message = "nombre no debe ser nulo")
    @Size(max = 200)
    private String nombre;
}
