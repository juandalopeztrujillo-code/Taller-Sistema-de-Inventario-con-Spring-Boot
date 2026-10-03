package com.cesde.sistema_de_inventario_con_springboot.presentation.controller;

import com.cesde.sistema_de_inventario_con_springboot.application.service.MovimientoService;
import com.cesde.sistema_de_inventario_con_springboot.domain.model.Movimiento;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;


    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping
    public ResponseEntity<List<Movimiento>> findAll() {
        List<Movimiento> movimientos = movimientoService.findAll();
        return ResponseEntity.ok(movimientos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> findById(@PathVariable Long id) {
        Movimiento movimiento = movimientoService.findById(id);
        return ResponseEntity.ok(movimiento);
    }

    @PostMapping
    public ResponseEntity<Movimiento> save(@RequestBody Movimiento movimiento) {
        Movimiento nuevoMovimiento = movimientoService.save(movimiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMovimiento);
    }

    @PutMapping
    public ResponseEntity<Movimiento> update(@RequestBody Movimiento movimiento) {
        Movimiento movimientoActualizado = movimientoService.update(movimiento);
        return ResponseEntity.ok(movimientoActualizado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> actualizarMovimiento(@PathVariable Long id, @RequestBody Movimiento movimiento) {
        movimiento.setMovimiento_id(id); // Asegúrate de asignar el ID de la URL al objeto
        Movimiento movimientoActualizado = movimientoService.update(movimiento);
        return ResponseEntity.ok(movimientoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movimientoService.delete(id);
        return ResponseEntity.noContent().build();

}
}

