/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author df
 */

/**
 * Clase utilitaria para validar entradas del usuario
 * Centraliza todas las validaciones de entrada en un solo lugar
 */
public class InputValidator {

    /**
     * Valida que una cadena no sea nula o vacía
     */
    public static boolean isValidString(String input) {
        return input != null && !input.trim().isEmpty();
    }

    /**
     * Valida y convierte un string a double
     * 
     * @return el valor convertido o null si es inválido
     */
    public static Double parseDouble(String input) {
        if (!isValidString(input)) {
            return null;
        }
        try {
            return Double.parseDouble(input.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Valida y convierte un string a entero
     * 
     * @return el valor convertido o null si es inválido
     */
    public static Integer parseInt(String input) {
        if (!isValidString(input)) {
            return null;
        }
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Valida que un precio sea positivo
     */
    public static boolean isValidPrice(Double price) {
        return price != null && price > 0;
    }

    /**
     * Valida que una cantidad sea positiva
     */
    public static boolean isValidQuantity(Integer quantity) {
        return quantity != null && quantity > 0;
    }

    /**
     * Valida que los meses de garantía no sean negativos
     */
    public static boolean isValidWarrantyMonths(Integer months) {
        return months != null && months >= 0;
    }

    /**
     * Valida que una opción de menú sea válida
     */
    public static boolean isValidMenuOption(String option) {
        return isValidString(option) &&
                (option.equals("1") || option.equals("2") ||
                        option.equals("3") || option.equals("4") ||
                        option.equals("5") || option.equals("6"));
    }

    /**
     * Valida que una opción de tipo de producto sea válida
     */
    public static boolean isValidProductTypeOption(String option) {
        return isValidString(option) && (option.equals("1") || option.equals("2"));
    }

    /**
     * Obtiene un mensaje de error para un campo inválido
     */
    public static String getErrorMessage(String fieldName, String input) {
        if (input == null || input.trim().isEmpty()) {
            return fieldName + " cannot be empty";
        }
        return "Invalid " + fieldName + " format";
    }
}
