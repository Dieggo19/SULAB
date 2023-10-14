package com.sulab.sulab.controllers;

import com.sulab.sulab.services.NotificacionService;
import com.sulab.sulab.models.Notificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {
    @Autowired
    private NotificacionService notificacionService;

    @GetMapping
    public ResponseEntity<List<Notificacion>> getNotificaciones() {
        List<Notificacion> notificaciones = notificacionService.traernotificaciones();
        if(notificaciones.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(notificaciones);
    }

    // Esta es por mientras, para probar la creacion y visualizacion de notificaciones
    // Debido a que esta se crea desde productos al hacer un cambio
    @PostMapping()
    public ResponseEntity<Notificacion> save(@RequestBody Notificacion notificacion) {
        Notificacion nuevaNotificacion = notificacionService.crearNotificacion(notificacion);
        return ResponseEntity.ok(nuevaNotificacion);
    }

}
