package com.fonyou.administracion.tesoreria.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "\"PAGOS\"")
@Data
@NoArgsConstructor
public class Pago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PAGOS")
    private int idPagos;


    @Basic(optional = false)
    @Column(name = "DIAS_LABORADOS")
    private int dias;


    @Basic(optional = false)
    @Column(name = "MES_LABORADO")
    private int mes;

    @Basic(optional = false)
    @Column(name = "ANIO_LABORADO")
    private int año;

    @Basic(optional = false)
    @Column(name = "ID_EMPLEADO")
    private int idEmpleado;

    @JoinColumn(name = "ID_EMPLEADO", referencedColumnName = "ID_EMPLEADO", insertable=false, updatable=false)
    @ManyToOne(optional = false)
    private Employee employee;


}

