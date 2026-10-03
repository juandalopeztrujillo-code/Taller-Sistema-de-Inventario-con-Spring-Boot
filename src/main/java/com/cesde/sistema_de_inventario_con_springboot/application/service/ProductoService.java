package com.cesde.sistema_de_inventario_con_springboot.application.service;

import com.cesde.sistema_de_inventario_con_springboot.domain.model.Producto;

import java.util.List;

public interface ProductoService {

    Producto save(Producto producto);
    List<Producto> findAll();
    Producto findById(Long id);
    void delete(Long id);
    Producto update(Producto producto);
}
