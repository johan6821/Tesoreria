package com.fonyou.administracion.tesoreria.repository;

import com.fonyou.administracion.tesoreria.entities.Pago;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Pagosrepository extends CrudRepository<Pago, Integer> {

    /*@Query(value = "SELECT e.NOMBRE_EMPLEADO, e.APELLIDO_EMPLEADO, e.SALARIO, p.DIAS_LABORADOS\n" +
            "            FROM fonyousch.employee e\n" +
            "            INNER JOIN fonyousch.pagos p\n" +
            "            ON p.ID_EMPLEADO =e.ID_EMPLEADO\n" +
            "            WHERE p.ID_EMPLEADO= ?1  and p.MES_LABORADO= ?2 and p.ANIO_LABORADO= ?3", nativeQuery = true)
    Pago  findDiasLaborados(@Param("idEmpleado")int idEmpleado, @Param("mes")int mes, @Param("anio")int anio);*/


    Pago findByIdEmpleadoAndAñoAndMes(int id, int anio, int mes);
   // Pago findByIdPagos(int id);




}
