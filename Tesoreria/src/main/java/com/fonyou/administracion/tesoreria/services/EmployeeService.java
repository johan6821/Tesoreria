package com.fonyou.administracion.tesoreria.services;

import com.fonyou.administracion.tesoreria.entities.Employee;
import com.fonyou.administracion.tesoreria.entities.Pago;
import com.fonyou.administracion.tesoreria.exception.ResourceNotFoundException;
import com.fonyou.administracion.tesoreria.repository.EmployeeRepository;
import com.fonyou.administracion.tesoreria.repository.Pagosrepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *  * @author Johan Céspedes Ortega
 *  * @date 7/12/2020 Clase para la implementación de las diferentes reglas de negocio que se generen alrededor del
 *  * Empleado.
 */
@Slf4j
@Service

public class EmployeeService implements ServiceInterface <Employee, Integer> {

@Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private final Pagosrepository pagosrepository;

    public EmployeeService(EmployeeRepository employeeRepository, Pagosrepository pagosrepository) {
        this.employeeRepository = employeeRepository;
        this.pagosrepository = pagosrepository;
    }

 @Override
    public Employee findById(Integer key) throws Exception {
     if (employeeRepository.findById(key).isPresent()) {
         return employeeRepository.findById(key).get();
     }
     throw new ResourceNotFoundException("El Empleado con id " + key + " no existe.");
    }


    public Float calculateSalary(Pago pago ) throws Exception {
        System.out.println("primer pago 10/12/2020 = " + pago);
        Employee employee = findById(pago.getIdEmpleado());
        Date fechaInicial = employee.getFechaIngreso();
        Date fechaFinal = employee.getFechaSalida();
        String fecha = pago.getAño()+"-"+pago.getMes()+"-"+30;
        Date date = getFecha(fecha);
        Boolean validarFecha = (date.after(fechaInicial) && date.before(fechaFinal));
        int mes = pago.getMes();
        int anio = pago.getAño();
        int id = pago.getIdEmpleado();
        if(validarFecha){
            System.out.println("idPago en el servicio  = " + id);
            Pago registroPago = pagosrepository.findByIdEmpleadoAndAñoAndMes(id, anio, mes);
            float  valorDia =   employee.getSalario()/30;
            float valorPago =  valorDia*registroPago.getDias();

            DecimalFormat df = new DecimalFormat("#.00");
            df.format(valorPago);
            return valorPago;
        } else {
            float pago1 = 0f;
            return pago1;
        }
    }

    @Override
    public List<Employee> findAll() throws Exception {
        if (employeeRepository.findAll()== null) {
            throw new ResourceNotFoundException("No existen Empleados creado en el sistema.");
        }
        return (List <Employee>)employeeRepository.findAll();
    }

    @Override
    public Employee create(Employee entity) throws Exception {

            if (entity.getNombre() == null || entity.getNombre().trim().isEmpty()) {
                throw new ResourceNotFoundException("El Nombre del empleado a Crear no puede estar vacio.");
            }
            if (entity.getApellido() == null || entity.getApellido().trim().isEmpty()) {
                throw new ResourceNotFoundException("El Apellido del empleado a Crear no puede estar vacio.");
            }
            if (entity.getFechaIngreso() == null) {
                throw new ResourceNotFoundException("La fecha no puede estar vacia.");
            }

           /*if (entity.getSalario() == null) {
                throw new ResourceNotFoundException("El valor del salario puede estar vacio.");
            }*/

            if (entity.getFechaSalida() != null) {
                if (entity.getFechaSalida().before(entity.getFechaIngreso())) {
                    throw new ResourceNotFoundException("la fecha de ingreso no puede ser mayor a la salida");
                }
            }

        return employeeRepository.save(entity);
    }

    @Override
    public Employee update(Employee entity) throws Exception {
        Employee employee= employeeRepository.findById(entity.getIdEmpleado()).get();

        if (employee == null) {
            throw new ResourceNotFoundException("El Empleado con Id " + entity.getIdEmpleado() + " no existe.");
        }
        if (entity.getNombre() == null || entity.getNombre().trim().isEmpty()) {
            entity.setNombre(employee.getNombre().toUpperCase());
        }
        employee.getNombre().toUpperCase();

        if (entity.getApellido() == null || entity.getApellido().trim().isEmpty()) {
            entity.setApellido(employee.getApellido().toUpperCase());
        }
        employee.getApellido().toUpperCase();

        if (entity.getFechaIngreso()== null ) {
            entity.setFechaIngreso(employee.getFechaIngreso());
        }

       /* if (entity.getSalario() == null || !entity.getSalario().toString().trim().isEmpty()) {
            entity.setSalario(employee.getSalario());
        }*/
        if (entity.getFechaSalida() == null ) {
            entity.setFechaSalida(employee.getFechaSalida());
        }

        if (entity.getFechaSalida() != null || !entity.getFechaSalida().toString().trim().isEmpty()) {
            if(entity.getFechaSalida().before(entity.getFechaIngreso())){
            throw new ResourceNotFoundException("la fecha de ingreso no puede ser mayor a la salida");
            }
        }
        return employeeRepository.save(entity);
    }

    @Override
    public void delete(Integer key) throws Exception {
        Employee employee= employeeRepository.findById(key).get();
        if (employee == null || key.toString().trim().isEmpty()) {
            throw new ResourceNotFoundException("El Empleado con Id " + key + " no existe." + "o no es valido el id ingresado");
        }
        employeeRepository.delete(employee);
    }

    public Boolean existeById (Integer key) throws Exception {
        if(key==null){
            throw new ResourceNotFoundException("El ID a validar, no puede estar vacio.");
        }
        if (employeeRepository.findById(key) == null) {
            return false;
        }
        return employeeRepository.existsById(key);
    }

    public static Date getFecha(String fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        }
        catch (ParseException ex)
        {
            System.out.println(ex);
        }
        return fechaDate;
    }

}
