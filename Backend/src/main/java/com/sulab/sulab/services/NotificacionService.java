package com.sulab.sulab.services;

import com.sulab.sulab.repositories.NotificacionRepository;
import com.sulab.sulab.models.Notificacion;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionService {

    @Autowired
    NotificacionRepository notificacionRepository;

    public Notificacion crearNotificacion(Notificacion notificacion){

        Notificacion notificacionNueva = new Notificacion();
        notificacionNueva.setTipo(notificacion.getTipo());
        notificacionNueva.setId_producto(notificacion.getId_producto());
        return notificacionRepository.save(notificacionNueva);
    }

    public List<Notificacion> traernotificaciones(){
        return notificacionRepository.findAll();
    }

    public Optional<Notificacion> findById(Integer id) {return notificacionRepository.findById(id); }

    public void borrarNotificacion(Integer id){notificacionRepository.deleteById(id);}
}