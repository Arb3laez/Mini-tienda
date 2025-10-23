/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author df
 */
public class PersistenciaException extends RuntimeException {

    /**
     * Constructor con mensaje personalizado
     * 
     * @param mensaje Descripción del error de persistencia
     */
    public PersistenciaException(String mensaje) {
        super(mensaje);
    }

    /**
     * Constructor con mensaje y causa
     * 
     * @param mensaje Descripción del error de persistencia
     * @param causa   Excepción que causó este error
     */
    public PersistenciaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    /**
     * Constructor con causa
     * 
     * @param causa Excepción que causó este error
     */
    public PersistenciaException(Throwable causa) {
        super(causa);
    }
}

