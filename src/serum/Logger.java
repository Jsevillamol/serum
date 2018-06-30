package serum;

import java.io.File;
import java.io.IOException;

/**
 * Esta clase se encarga de la salida.
 * Tanto de escribir en el fichero objeto, como de escribir en un log,
 * como de mostrar los errores en pantalla.
 * @author David Rubio
 */
public abstract class Logger {

    public static java.io.PrintStream log;

    static {
        java.io.File file = new File("log.txt");
        try {
            file.createNewFile();
            log = new java.io.PrintStream(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static java.io.PrintStream codeExit;

    static {
        java.io.File file = new File("code.p");
        try {
            file.createNewFile();
            java.util.Scanner sc = new java.util.Scanner(AnalizadorSintactico.file);
            codeExit = new java.io.PrintStream(file);
            /*El principio del fichero, en un comentario, copiamos el código fuente:*/
            codeExit.println("{");
            while (sc.hasNextLine())
                codeExit.println(sc.nextLine());
            codeExit.println("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void report_error(String message){
        log.println(message);
        System.err.println(message);
    }
}
