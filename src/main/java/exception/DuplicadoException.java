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
 * Excepción personalizada para registros con nombre repetido.
 * Se lanza cuando se intenta crear o actualizar un producto con un nombre que
 * ya existe.
 */

public class DuplicadoException extends RuntimeException {

    /**
     * Constructor con mensaje personalizado
     * 
     * @param mensaje Descripción del error de duplicado
     */
    public DuplicadoException(String mensaje) {
        super(mensaje);
    }

    /**
     * Constructor con mensaje y causa
     * 
     * @param mensaje Descripción del error de duplicado
     * @param causa   Excepción que causó este error
     */
    public DuplicadoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    /**
     * Constructor con causa
     * 
     * @param causa Excepción que causó este error
     */
    public DuplicadoException(Throwable causa) {
        super(causa);
    }
}
