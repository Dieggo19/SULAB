package com.sulab.sulab.repositories;

import com.sulab.sulab.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query(value = "SELECT e FROM Producto e WHERE e.stockActual <= e.stockMin")
    List<Producto> findBajoStock();

}
