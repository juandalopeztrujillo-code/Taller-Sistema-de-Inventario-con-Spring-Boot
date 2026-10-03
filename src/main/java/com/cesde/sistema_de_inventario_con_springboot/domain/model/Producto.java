package com.cesde.sistema_de_inventario_con_springboot.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long producto_id;

    @NotBlank(message = "el nombre pa el producto es obligatorio entonces tampoco puede estar vacio")
    @Size(max = 100)
    private String nombre;

    @Size(max = 255)
    private String descripcion;

    @NonNull()
    @Min(0)
    private Double precio;

    @NotNull
    @Min(0)
    private Integer stock;

    // hicimos este metodo para aumentar el stock del producto
    public void sumarStock(Integer cantidad){
        this.stock += cantidad;
    }

    // y este pues lo mismo pero para bajarlo
    public void restarStock(Integer cantidad){
        this.stock -= cantidad;
    }

}
