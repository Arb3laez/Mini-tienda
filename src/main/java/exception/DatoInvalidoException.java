/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author df
 */
/**
 * Excepción personalizada para datos vacíos o inválidos.
 * Se lanza cuando los datos de entrada no cumplen con las validaciones
 * requeridas.
 */
public class DatoInvalidoException extends RuntimeException {

    /**
     * Constructor con mensaje personalizado
     * 
     * @param mensaje Descripción del error de validación
     */
    public DatoInvalidoException(String mensaje) {
        super(mensaje);
    }

    /**
     * Constructor con mensaje y causa
     * 
     * @param mensaje Descripción del error de validación
     * @param causa   Excepción que causó este error
     */
    public DatoInvalidoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    /**
     * Constructor con causa
     * 
     * @param causa Excepción que causó este error
     */
    public DatoInvalidoException(Throwable causa) {
        super(causa);
    }
}