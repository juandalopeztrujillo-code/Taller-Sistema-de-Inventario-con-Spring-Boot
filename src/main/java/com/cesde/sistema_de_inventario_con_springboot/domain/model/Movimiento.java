package com.cesde.sistema_de_inventario_con_springboot.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movimientos")

public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movimiento_id;


    @ManyToOne
    @JoinColumn(name = "producto_id")
    @NonNull
    private Producto producto;

    @NotNull
    @Min(1)
    private Integer cantidad;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipo;
}
