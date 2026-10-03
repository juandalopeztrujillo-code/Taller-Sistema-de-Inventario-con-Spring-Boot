package com.cesde.sistema_de_inventario_con_springboot.infrastructure.repository;

import com.cesde.sistema_de_inventario_con_springboot.domain.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
}
