package com.sulab.sulab.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//No tiene FK. Borrar linea cuando se coloque
@Entity
@Table(name = "Producto")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String clave;
    private String nombre;
    private int stockActual;
    private int stockMin;
    private int precioSinIva;
    private int precioConIva;
    private int unidadVenta;

    //atributo provisional para contar con categoria
    private String categoria;
}


