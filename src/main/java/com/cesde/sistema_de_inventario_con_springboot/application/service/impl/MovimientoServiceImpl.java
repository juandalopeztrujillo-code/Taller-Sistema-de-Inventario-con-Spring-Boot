package com.cesde.sistema_de_inventario_con_springboot.application.service.impl;

import com.cesde.sistema_de_inventario_con_springboot.application.service.MovimientoService;
import com.cesde.sistema_de_inventario_con_springboot.domain.model.Movimiento;
import com.cesde.sistema_de_inventario_con_springboot.domain.model.Producto;
import com.cesde.sistema_de_inventario_con_springboot.domain.model.TipoMovimiento;
import com.cesde.sistema_de_inventario_con_springboot.infrastructure.exception.InsufficientStockException;
import com.cesde.sistema_de_inventario_con_springboot.infrastructure.exception.ResourceNotFoundException;
import com.cesde.sistema_de_inventario_con_springboot.infrastructure.exception.ValidationException;
import com.cesde.sistema_de_inventario_con_springboot.infrastructure.repository.MovimientoRepository;
import com.cesde.sistema_de_inventario_con_springboot.infrastructure.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final ProductoRepository productoRepository;

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository, ProductoRepository productoRepository) {
        this.movimientoRepository = movimientoRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public Movimiento save(Movimiento movimiento) {

        Long productoId = movimiento.getProducto().getProducto_id();
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new ResourceNotFoundException("El producto producto de este movimiento no está con este id: " + productoId));

        if (movimiento.getTipo() == TipoMovimiento.ENTRADA) {
            producto.sumarStock(movimiento.getCantidad());
        } else if (movimiento.getTipo() == TipoMovimiento.SALIDA) {
            if (producto.getStock() < movimiento.getCantidad()) {
                throw new InsufficientStockException("Stock insuficiento, ahora solo hay disponible: " + producto.getStock() + ", solicitado: " + movimiento.getCantidad());
            }
            producto.restarStock(movimiento.getCantidad());
        }

        productoRepository.save(producto);
        if (movimiento.getCantidad() <= 0) {
            throw new ValidationException("La cantidad del movimiento debe ser mayor a 0");
        }


        if (movimiento.getTipo() == null) {
            throw new ValidationException("El tipo de movimiento es obligatorio (ENTRADA o SALIDA)");
        }

        movimiento.setProducto(producto);
        return movimientoRepository.save(movimiento);
    }

    @Override
    public List<Movimiento> findAll() {
        return movimientoRepository.findAll();
    }

    @Override
    public Movimiento findById(Long id) {
        return movimientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("el movimiento no fue encontrado con este id: " + id));
    }

    @Override
    public void delete(Long id) {
        Movimiento movimiento = findById(id);
        movimientoRepository.delete(movimiento);
    }

    @Override
    public Movimiento update(Movimiento movimiento) {
        findById(movimiento.getMovimiento_id());
        return movimientoRepository.save(movimiento);
    }
}