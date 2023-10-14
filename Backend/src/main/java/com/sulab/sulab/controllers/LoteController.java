package com.sulab.sulab.controllers;

import com.sulab.sulab.services.LoteService;
import com.sulab.sulab.models.Lote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/lotes")
public class LoteController {
    @Autowired
    private LoteService loteService;

    @GetMapping
    public ResponseEntity<List<Lote>> traerlotes() {
        List<Lote> lotes = loteService.getAll();
        if(lotes.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lotes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lote> traerLotePorID(@PathVariable("id") String id) {

        Integer id_lote = Integer.parseInt(id);
        Lote lote = loteService.getLote(id_lote);
        if(lote == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lote);
    }

    @PostMapping()
    public ResponseEntity<Lote> save(@RequestBody Lote lote) {
        Lote nuevoLote = loteService.createLote(lote);
        return ResponseEntity.ok(nuevoLote);
    }

    @DeleteMapping()
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        Integer id_lote = Integer.parseInt(id);
        return ResponseEntity.ok(loteService.deleteLote(id_lote));
    }


}
