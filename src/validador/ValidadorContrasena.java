package validador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

class ValidadorContrasena implements Runnable {
    private final String contrasena;
    private static final String ARCHIVO_REGISTRO = "registro_validacion.txt";

    public ValidadorContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public void run() {
        // Validar la contraseña y escribir el resultado en el archivo
        String resultado = validarContrasena();
        escribirResultadoEnArchivo(resultado);
    }

    private String validarContrasena() {
        // Validar longitud mínima
        boolean esValida = esLongitudValida() &&
                tieneCaracterEspecial() &&
                tieneMayusculas() &&
                tieneMinusculas() &&
                tieneNumeros();
        return "La contraseña \"" + contrasena + "\" es " + (esValida ? "válida." : "NO válida.");
    }

    private boolean esLongitudValida() {
        // Verifica que la contraseña tenga al menos 8 caracteres
        return contrasena.length() >= 8;
    }

    private boolean tieneCaracterEspecial() {
        // Usa una expresión regular para encontrar caracteres especiales
        return Pattern.compile("[!@#$%^&*()-+]").matcher(contrasena).find();
    }

    private boolean tieneMayusculas() {
        // Cuenta el número de letras mayúsculas usando una expresión regular
        return Pattern.compile("[A-Z]").matcher(contrasena).results().count() >= 2;
    }

    private boolean tieneMinusculas() {
        // Cuenta el número de letras minúsculas usando una expresión regular
        return Pattern.compile("[a-z]").matcher(contrasena).results().count() >= 3;
    }

    private boolean tieneNumeros() {
        // Verifica que haya al menos un número
        return Pattern.compile("[0-9]").matcher(contrasena).find();
    }

    private void escribirResultadoEnArchivo(String resultado) {
        // Escribe el resultado en un archivo de texto
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_REGISTRO, true))) {
            writer.write(resultado);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
