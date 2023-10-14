package com.sulab.sulab.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//No tiene FK. Borrar linea cuando se coloque
@Entity
@Table(name = "Precio")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Precio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int monto;
    private Date fecha;
}
