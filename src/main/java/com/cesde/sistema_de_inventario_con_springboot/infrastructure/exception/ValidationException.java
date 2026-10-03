package com.cesde.sistema_de_inventario_con_springboot.infrastructure.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
