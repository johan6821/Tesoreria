package com.fonyou.administracion.tesoreria.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "\"EMPLOYEE\"")
@Data
@NoArgsConstructor


public class Employee  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EMPLEADO")
    private int idEmpleado;

    @Basic(optional = false)
    @NotEmpty(message="El nombre del Empleado es obligatorio")
    @Column(name = "NOMBRE_EMPLEADO")
    private String nombre;

    @Basic(optional = false)
    @NotEmpty(message="El apellido del Empleado es obligatorio")
    @Column(name = "APELLIDO_EMPLEADO")
    private String apellido;

    @Basic(optional = false)
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd")
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaIngreso;

    @Basic(optional = false)
    @NotNull
    @Column(name = "SALARIO")
    private float salario;

    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd")
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaSalida;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    @ToString.Exclude
    private Collection<Pago> pagos;

    public Employee(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
}
