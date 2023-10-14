package com.sulab.sulab;

import com.sulab.sulab.models.Notificacion;
import com.sulab.sulab.repositories.NotificacionRepository;
import com.sulab.sulab.services.NotificacionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NotificacionServiceTest {

    @InjectMocks
    private NotificacionService notificacionService;

    @Mock
    private NotificacionRepository notificacionRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCrearNotificacion() {
        Notificacion notificacion = new Notificacion();
        notificacion.setTipo("BajoStock");
        notificacion.setId_producto(1);

        Mockito.when(notificacionRepository.save(notificacion)).thenReturn(notificacion);

        Notificacion result = notificacionService.crearNotificacion(notificacion);

        Assertions.assertEquals(notificacion, result);
    }

    @Test
    public void testTraerNotificaciones() {
        List<Notificacion> notificaciones = new ArrayList<>();
        Mockito.when(notificacionRepository.findAll()).thenReturn(notificaciones);

        List<Notificacion> result = notificacionService.traernotificaciones();
        Assertions.assertEquals(notificaciones, result);
    }

    @Test
    public void testFindById() {
        Integer notificacionId = 1;
        Notificacion notificacion = new Notificacion();
        Mockito.when(notificacionRepository.findById(notificacionId)).thenReturn(Optional.of(notificacion));

        Optional<Notificacion> result = notificacionService.findById(notificacionId);

        Assertions.assertEquals(Optional.of(notificacion), result);
    }

    @Test
    public void testBorrarNotificacion() {
        Integer notificacionId = 1;

        // Configurar el comportamiento del repositorio simulado
        doNothing().when(notificacionRepository).deleteById(notificacionId);

        // Llamar al método que queremos probar
        notificacionService.borrarNotificacion(notificacionId);

        // Verificar que el método deleteById del repositorio se llamó una vez con el ID correcto
        verify(notificacionRepository, times(1)).deleteById(notificacionId);

    }

}
