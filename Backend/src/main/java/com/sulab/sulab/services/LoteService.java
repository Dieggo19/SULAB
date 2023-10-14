package com.sulab.sulab.services;

import com.sulab.sulab.repositories.LoteRepository;
import com.sulab.sulab.models.Lote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoteService {

    @Autowired
    LoteRepository loteRepository;

    public Lote createLote(Lote lote) {
        return loteRepository.save(lote);
    }

    // Read
    public Lote getLote(Integer id) {
        return loteRepository.findById(id).orElse(null);
    }

    // Get all
    public List<Lote> getAll() {
        return loteRepository.findAll();
    }

    // Update
    /* QUEDA COMENTADA HASTA ESTUDIAR BIEN EL TEMA DE LOS LOTES, SI ES QUE SE PUEDEN MODIFICAR
    public Lote updateLote(Lote lote) {
        Lote existingLote = loteRepository.findById(lote.getId()).orElse(null);
        existingLote.setNombreClave(lote.getNombreClave());
        existingLote.setFecha_prod(lote.getFecha_prod());
        existingLote.setFecha_venc(lote.getFecha_venc());
        existingLote.setCantidad(lote.getCantidad());
        existingLote.setId_producto(lote.getId_producto());
        existingLote.setId_proveedor(lote.getId_proveedor());

        return loteRepository.save(existingLote);
    }
    */

    // Delete
    public String deleteLote(Integer id) {
        loteRepository.deleteById(id);
        return "Lote eliminado: " + id;
    }
}
