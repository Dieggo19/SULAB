package com.sulab.sulab;

import com.sulab.sulab.models.Lote;
import com.sulab.sulab.repositories.LoteRepository;
import com.sulab.sulab.services.LoteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoteServiceTest {

    @InjectMocks
    private LoteService loteService;

    @Mock
    private LoteRepository loteRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateLote() {
        // Crear un lote simulado
        Lote lote = new Lote();
        lote.setNombreClave("Lote1");
        lote.setFecha_prod(LocalDate.now());
        lote.setFecha_venc(LocalDate.now().plusDays(30));
        lote.setCantidad(100);
        lote.setId_producto(1);
        lote.setId_proveedor(1);

        // Configurar el comportamiento del repositorio simulado
        Mockito.when(loteRepository.save(lote)).thenReturn(lote);

        // Llamar a la función que queremos probar
        Lote createdLote = loteService.createLote(lote);

        // Verificar que el lote se haya creado correctamente
        Assertions.assertEquals("Lote1", createdLote.getNombreClave());
    }

    @Test
    public void testGetLote() {
        // Identificador del lote a obtener
        Integer loteId = 1;

        // Crear un lote simulado
        Lote lote = new Lote();
        lote.setId(loteId);
        lote.setNombreClave("Lote1");

        // Configurar el comportamiento del repositorio simulado
        Mockito.when(loteRepository.findById(loteId)).thenReturn(Optional.of(lote));

        // Llamar a la función que queremos probar
        Lote retrievedLote = loteService.getLote(loteId);

        // Verificar que el lote se haya obtenido correctamente
        Assertions.assertEquals("Lote1", retrievedLote.getNombreClave());
    }

    @Test
    public void testGetAll() {
        // Crear una lista de lotes simulados
        List<Lote> lotesSimulados = new ArrayList<>();
        lotesSimulados.add(new Lote());
        lotesSimulados.add(new Lote());

        // Configurar el comportamiento del repositorio simulado
        Mockito.when(loteRepository.findAll()).thenReturn(lotesSimulados);

        // Llamar a la función que queremos probar
        List<Lote> allLotes = loteService.getAll();

        // Verificar que se devuelva la lista de lotes simulados correctamente
        Assertions.assertEquals(2, allLotes.size());
    }

    @Test
    public void testDeleteLote(){
        // Identificador del lote a eliminar
        Integer loteId = 1;

        // Configurar el comportamiento del repositorio simulado
        doNothing().when(loteRepository).deleteById(loteId);

        // Llamar a la función que queremos probar
        String result = loteService.deleteLote(loteId);

        // Verificar que la función devuelve el mensaje esperado
        Assertions.assertEquals("Lote eliminado: " + loteId, result);

    }
}
