package com.crud.producto.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoModel implements Serializable {

    @NotBlank(message = "codigo no debe ser vacio")
    @NotNull(message = "codigo no debe ser nulo")
    @Size(max = 20)
    private String codigo;

    @NotBlank(message = "nombre no debe ser vacio")
    @NotNull(message = "nombre no debe ser nulo")
    @Size(max = 200)
    private String nombre;
}
