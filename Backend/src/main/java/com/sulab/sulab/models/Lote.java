package com.sulab.sulab.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

//No tiene FK. Borrar linea cuando se coloque
@Entity
@Table(name = "Lote")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Lote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombreClave;
    private LocalDate fecha_prod;
    private LocalDate fecha_venc;
    private int cantidad;

    //LLaves foraneas
    private int id_producto;
    private int id_proveedor;
}