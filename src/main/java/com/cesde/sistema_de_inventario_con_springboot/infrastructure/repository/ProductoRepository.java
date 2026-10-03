package com.cesde.sistema_de_inventario_con_springboot.infrastructure.repository;

import com.cesde.sistema_de_inventario_con_springboot.domain.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByNombre(String nombre);
}
