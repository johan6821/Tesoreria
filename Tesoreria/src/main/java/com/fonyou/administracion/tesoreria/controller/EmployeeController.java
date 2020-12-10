package com.fonyou.administracion.tesoreria.controller;

import com.fonyou.administracion.tesoreria.dto.EmployeeOutputDTO;
import com.fonyou.administracion.tesoreria.dto.GeneralOutput;
import com.fonyou.administracion.tesoreria.entities.Employee;
import com.fonyou.administracion.tesoreria.entities.Pago;
import com.fonyou.administracion.tesoreria.exception.ResourceNotFoundException;
import com.fonyou.administracion.tesoreria.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @ResponseBody
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public GeneralOutput getById(@PathVariable Integer id) throws Exception{
        Employee employee = employeeService.findById(id);
        if(employee==null){
            throw new  ResourceNotFoundException("El Empleado con id " + id + " no existe.");
        }
        return new GeneralOutput(HttpStatus.OK.value(),HttpStatus.OK.getReasonPhrase(), EmployeeOutputDTO.getDTO(employeeService.findById(id)));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAll() throws Exception {
        List<Employee> list = employeeService.findAll();
        if(!list.isEmpty()){
            return new ResponseEntity<List>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay registros",HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity <?> create(@RequestBody Employee employee) throws Exception {
        return new ResponseEntity<>(employeeService.create(employee), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity <?> update(@PathVariable("id") Integer id, @RequestBody Employee entity) throws Exception {
        Employee employee = employeeService.findById(id);
        if(employee==null){
            return new ResponseEntity<>("No existe un Empleado correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        entity.setIdEmpleado(id);
        return new ResponseEntity<>(employeeService.update(entity),HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("{id}")
    public void deleteEmployee(@PathVariable("id") Integer id) throws Exception {
        try {
        employeeService.delete(id);
        }
        catch (Exception e){
            throw new  ResourceNotFoundException("Error al imprimir el Empleado con id  " + id + " verifique el id ei ingresado.  " + e);
        }
    }

   @ResponseBody
    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity <?> employeePay(@RequestBody Pago pago) throws Exception {
       log.info("pago en el controller= " + pago);
       log.info("pago.getID_EMPLEADO() en controller = " + pago.getIdEmpleado());
        return new ResponseEntity<>(employeeService.calculateSalary(pago), HttpStatus.OK);
    }

}
