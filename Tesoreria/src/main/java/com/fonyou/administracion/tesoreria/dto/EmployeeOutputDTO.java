package com.fonyou.administracion.tesoreria.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fonyou.administracion.tesoreria.entities.Employee;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class EmployeeOutputDTO {

    private int idEmpleado;

    private String nombre;
    private String apellido;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fechaIngreso;
    private float salario;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fechaSalida;


    public static EmployeeOutputDTO getDTO (Employee employee) {
        EmployeeOutputDTO employeeOutputDTO = new EmployeeOutputDTO();
        employeeOutputDTO.setIdEmpleado(employee.getIdEmpleado());
        employeeOutputDTO.setNombre(employee.getNombre());
        employeeOutputDTO.setApellido(employee.getApellido());
        employeeOutputDTO.setFechaIngreso(employee.getFechaIngreso());
        employeeOutputDTO.setSalario(employee.getSalario());
        employeeOutputDTO.setFechaSalida(employee.getFechaSalida());
        return employeeOutputDTO;

    }
}
