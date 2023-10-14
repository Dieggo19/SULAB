package com.sulab.sulab.services;

import com.sulab.sulab.models.Notificacion;
import com.sulab.sulab.models.Producto;
import com.sulab.sulab.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.processing.Generated;
import java.util.List;
@Service
public class ProductoService {
    @Autowired
    ProductoRepository productoRepository;

    NotificacionService notificacionService;

    // CRUD
    // Create
    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Read
    public Producto getProducto(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    // Get all
    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    public List<Producto> getBajoSock() {
        return productoRepository.findBajoStock();
    }

    // Update
    public Producto updateProducto(Producto producto) {
        Producto existingProducto = productoRepository.findById(producto.getId()).orElse(null);
        if (existingProducto == null) {
            return null;
        }
        else{
            existingProducto.setClave(producto.getClave());
            existingProducto.setNombre(producto.getNombre());
            existingProducto.setStockActual(producto.getStockActual());
            existingProducto.setStockMin(producto.getStockMin());
            existingProducto.setPrecioSinIva(producto.getPrecioSinIva());
            existingProducto.setPrecioConIva(producto.getPrecioConIva());
            existingProducto.setUnidadVenta(producto.getUnidadVenta());
            existingProducto.setCategoria(producto.getCategoria());
        }
        if (producto.getStockActual() <= producto.getStockMin()){
            Notificacion notificacion = new Notificacion();
            notificacion.setTipo("BajoStock");
            notificacion.setId_producto(producto.getId());
            notificacionService.crearNotificacion(notificacion);
        }
        return productoRepository.save(existingProducto);
    }

    // Delete
    public String deleteProducto(Integer id) {
        productoRepository.deleteById(id);
        return "Producto eliminado: " + id;
    }
}
