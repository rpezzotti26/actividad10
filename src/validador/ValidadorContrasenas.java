package validador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ValidadorContrasenas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Thread> hilos = new ArrayList<>();

        System.out.println("Ingrese las contraseñas separadas por coma (por ejemplo: Pass1234!, Abcdef123, etc.):");
        String[] contrasenas = scanner.nextLine().split(",");

        for (String contrasena : contrasenas) {
            // Crear un hilo para cada contraseña
            Thread hilo = new Thread(new ValidadorContrasena(contrasena.trim()));
            hilos.add(hilo);
            hilo.start();
        }

        for (Thread hilo : hilos) {
            try {
                // Espera a que todos los hilos terminen
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        scanner.close();
    }
}
