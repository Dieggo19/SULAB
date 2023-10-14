package com.sulab.sulab.controllers;

import com.sulab.sulab.models.Producto;
import com.sulab.sulab.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> traerProductos() {
        List<Producto> productos = productoService.getAll();
        if(productos.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> traerProductoPorID(@PathVariable("id") String id) {

        Integer id_prod = Integer.parseInt(id);
        Producto producto = productoService.getProducto(id_prod);
        if(producto == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(producto);
    }

    @GetMapping("/bajo-stock")
    public ResponseEntity<List<Producto>> bajoStock() {
        List<Producto> productos = productoService.getBajoSock();
        if(productos.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(productos);
    }

    @PostMapping()
    public ResponseEntity<Producto> save(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.createProducto(producto);
        return ResponseEntity.ok(nuevoProducto);
    }

    @PutMapping()
    public ResponseEntity<Producto> update(@RequestBody Producto producto) {
        Producto productoModificado = productoService.updateProducto(producto);
        return ResponseEntity.ok(productoModificado);
    }

}
