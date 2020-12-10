package com.fonyou.administracion.tesoreria.exception;
/**
 *  @author Johan Céspedes Ortega
 *  @date 7/12/2020 Modela los mensajes de error que produce la aplicacion cuando el dato solicitado no existeKey.
 *
 */

public class ResourceNotFoundException extends RuntimeException {
    public static final String DESCRIPCION = "Recurso sin resultados";

    public ResourceNotFoundException(){
        super(DESCRIPCION);
    }

    public ResourceNotFoundException(String detalleError){
        super(DESCRIPCION+" : "+detalleError);
    }
}
