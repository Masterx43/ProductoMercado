package com.mercado.Productos.repository;

import org.springframework.stereotype.Repository;

import com.mercado.Productos.model.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductoRepository {

    // Nuestra "Base de Datos" temporal
    private final List<Producto> db = new ArrayList<>();
    
    // Para generar IDs de forma segura
    private final AtomicLong idGenerator = new AtomicLong(1);

    // Guardar un producto
    public Producto save(Producto producto) {
        if (producto.getId() == null) {
            producto.setId(idGenerator.getAndIncrement());
        }
        db.add(producto);
        return producto;
    }

    // Listar todos
    public List<Producto> findAll() {
        return new ArrayList<>(db); // Retornamos copia para proteger la original
    }

    // Buscar por ID
    public Optional<Producto> findById(Long id) {
        return db.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public void delete(Long id){
        Optional<Producto> producto = findById(id);
        if (producto != null){
            int id2 = id.intValue();
            db.remove(id2);
        }
    }
}
