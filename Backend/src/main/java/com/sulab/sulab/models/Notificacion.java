package com.sulab.sulab.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Notificacion")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Notificacion{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tipo;

    //llave foranea, no se le agregan lineas extras para definirla, debido que se crea desde productos
    private Integer id_producto; // Debes crear una relación con la entidad Producto

}
