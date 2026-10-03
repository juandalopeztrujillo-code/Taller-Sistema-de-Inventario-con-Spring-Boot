package com.cesde.sistema_de_inventario_con_springboot.application.service.impl;

import com.cesde.sistema_de_inventario_con_springboot.application.service.ProductoService;
import com.cesde.sistema_de_inventario_con_springboot.domain.model.Producto;
import com.cesde.sistema_de_inventario_con_springboot.infrastructure.exception.DuplicateResourceException;
import com.cesde.sistema_de_inventario_con_springboot.infrastructure.exception.ResourceNotFoundException;
import com.cesde.sistema_de_inventario_con_springboot.infrastructure.exception.ValidationException;
import com.cesde.sistema_de_inventario_con_springboot.infrastructure.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Producto save(Producto producto) {

        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new ValidationException("El nombre del producto no puede estar vacío");
        }

        // Validamos que el precio sea mayor a cero
        if (producto.getPrecio() == null || producto.getPrecio() <= 0) {
            throw new ValidationException("El precio del producto debe ser mayor a 0");
        }

        // Validamos que el stock inicial no sea negativo
        if (producto.getStock() < 0) {
            throw new ValidationException("El stock inicial no puede ser negativo");
        }

        if (productoRepository.findByNombre(producto.getNombre()).isPresent()) {
            throw new DuplicateResourceException("hay un producto q ya tiene ese nombre: " + producto.getNombre());
        }
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto findById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("el id del producto no se encuentra: " + id));
    }

    @Override
    public void delete(Long id) {
        Producto producto = findById(id);
        productoRepository.delete(producto);
    }

    @Override
    public Producto update(Producto producto) {
        findById(producto.getProducto_id());
        return productoRepository.save(producto);
    }
}
