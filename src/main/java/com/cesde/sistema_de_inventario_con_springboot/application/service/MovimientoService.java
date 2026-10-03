package com.cesde.sistema_de_inventario_con_springboot.application.service;

import com.cesde.sistema_de_inventario_con_springboot.domain.model.Movimiento;

import java.util.List;

public interface MovimientoService {

    Movimiento save(Movimiento movimiento);
    List<Movimiento> findAll();
    Movimiento findById(Long id);
    void delete(Long id);
    Movimiento update(Movimiento movimiento);
}
