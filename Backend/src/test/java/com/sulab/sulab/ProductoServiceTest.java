package com.sulab.sulab;

import com.sulab.sulab.models.Notificacion;
import com.sulab.sulab.models.Producto;
import com.sulab.sulab.repositories.ProductoRepository;
import com.sulab.sulab.services.NotificacionService;
import com.sulab.sulab.services.ProductoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @InjectMocks
    private ProductoService productoService;

    @Mock
    private ProductoRepository productoRepository;
    @Mock
    private NotificacionService notificacionService;

    @Test
    public void testCreateProducto() {
        Producto producto = new Producto();
        producto.setId(1);
        producto.setClave("clave1");
        producto.setNombre("Producto1");
        producto.setCategoria("Insumo Medico");
        producto.setStockActual(30);
        producto.setStockMin(5);
        producto.setPrecioConIva(100);
        producto.setPrecioSinIva(80);
        producto.setUnidadVenta(1);

        // Configuramos el comportamiento esperado del repositorio
        Mockito.when(productoRepository.save(producto)).thenReturn(producto);

        // Llamamos al método que queremos probar
        Producto result = productoService.createProducto(producto);

        // Verificamos que el resultado sea el esperado
        assertEquals(producto, result);

        // Verificamos que el método save se llamó exactamente una vez
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    public void testGetAll() {
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1,"CLAVE1", "Producto 1", 10, 120, 100, 50, 1, "Insumo Médico"));
        productos.add(new Producto(2,"CLAVE2", "Producto 2", 10, 120, 100, 50, 1, "Insumo Médico"));
        Mockito.when(productoRepository.findAll()).thenReturn(productos);
        ArrayList<Producto> proveedoresTest = (ArrayList<Producto>) productoService.getAll();
        Assertions.assertEquals(productos.size(), proveedoresTest.size());
    }

    @Test
    public void testGetProducto() {
        Integer id = 1;
        String clave = "CLAVE1";
        String nombre = "producto 1";
        int stockActual = 10;
        int stockMinimo = 3;
        int precioIva = 100;
        int precioSinIva = 80;
        int unidadVenta = 1;
        String categoria = "insumo medico";

        Mockito.when(productoRepository.findById(id)).thenReturn(Optional.of(new Producto(id, clave, nombre, stockActual, stockMinimo, precioIva, precioSinIva, unidadVenta, categoria)));
        Producto producto = productoService.getProducto(id);
        Assertions.assertEquals(id, producto.getId());

    }

    @Test
    public void testGetBajoStock(){
        // Crear una lista de productos simulados con stock bajo
        Producto producto1 = new Producto();
        producto1.setId(1);
        producto1.setStockActual(5);
        producto1.setStockMin(10);

        Producto producto2 = new Producto();
        producto2.setId(2);
        producto2.setStockActual(8);
        producto2.setStockMin(15);

        List<Producto> productosBajoStockSimulados = new ArrayList<>();
        productosBajoStockSimulados.add(producto1);
        productosBajoStockSimulados.add(producto2);

        // Configurar el comportamiento del repositorio simulado
        Mockito.when(productoRepository.findBajoStock()).thenReturn(productosBajoStockSimulados);

        // Llamar a la función que queremos probar
        List<Producto> productosBajoStock = productoService.getBajoSock();

        // Verificar que la función devuelva los productos simulados correctamente
        Assertions.assertEquals(productosBajoStockSimulados, productosBajoStock);
    }

    @Test
    public void testUpdateProducto() {
        // Crear un producto simulado existente
        Producto producto = new Producto();
        producto.setId(1);
        producto.setStockActual(5);
        producto.setStockMin(10);

        // Producto actualizado
        Producto productoActualizado = new Producto();
        productoActualizado.setId(1);
        productoActualizado.setStockActual(8);
        productoActualizado.setStockMin(10); // Mantener el stock mínimo igual en la actualización

        // Configurar el comportamiento del repositorio simulado
        when(productoRepository.findById(producto.getId())).thenReturn(Optional.of(producto));
        when(productoRepository.save(producto)).thenReturn(productoActualizado);

        // Llamar a la función que queremos probar con un producto que existe
        Producto updatedProducto = productoService.updateProducto(productoActualizado);

        // Verificar que el producto se actualizó correctamente
        assertEquals(productoActualizado, updatedProducto);

        // Llamar a la función con un producto que no existe
        Producto productoNoExistente = new Producto();
        productoNoExistente.setId(2); // Un id que no existe en el repositorio

        // Configurar el comportamiento del repositorio simulado para el producto no existente
        when(productoRepository.findById(productoNoExistente.getId())).thenReturn(Optional.empty());

        // Llamar a la función con un producto que no existe
        Producto updatedProductoNoExistente = productoService.updateProducto(productoNoExistente);

        // Verificar que la función devuelve null cuando el producto no existe
        Assertions.assertNull(updatedProductoNoExistente);
    }


    @Test
    public void testDeleteProducto(){
    // Identificador del producto a eliminar
    Integer productId = 1;

    // Configurar el comportamiento del repositorio simulado
    doNothing().when(productoRepository).deleteById(productId);

    // Llamar a la función que queremos probar
    String result = productoService.deleteProducto(productId);

    // Verificar que la función devuelve el mensaje esperado
    Assertions.assertEquals("Producto eliminado: " + productId, result);
}

}
