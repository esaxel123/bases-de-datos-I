import java.io.BufferedReader; // BufferedReader: Lee texto de un flujo de entrada de caracteres, almacenando los caracteres en un búfer para leerlos de forma eficiente.
import java.io.FileReader;    // FileReader: Lee archivos de caracteres.
import java.io.IOException;   // IOException: Maneja posibles errores que puedan ocurrir durante las operaciones de entrada/salida.
import java.util.HashMap;     // HashMap: Almacena datos en pares clave-valor. Es ideal para contar ocurrencias.
import java.util.Map;
public class Main {
    public static void main(String[] args) {

        String archivoCSV = "codigos_postales_hmo.csv";// Declaramos una variable String para el nombre del archivo CSV.

        String linea;//variable para almacenar cada linea del archivo

        String separador = ",";//delimitador que se usa en el archivo, una coma

        Map<String, Integer> recuentoCodigosPostales = new HashMap<>();//se crea un hashmash para almacenar los codigos postales y el numero de asentaqmiantos

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {//usamos try y cerramos con cath en caso de cualquier error, ademas aqui se empieza a leer el archivo

            while ((linea = br.readLine()) != null) {//este ciclo while lee cada linea del archivo hasta que no haya mas,y se guarda en la variable linea


                String[] datos = linea.split(separador); // aqui se divide la línea actual en un array de Strings usando la coma como separador.


                String codigoPostal = datos[0];//se asigna el primer elemento del array(osea el codigo postal en este caso)

                // Actualizamos el recuento en el HashMap.
                // .getOrDefault(clave, valor_defecto) obtiene el valor de la clave, o devuelve 0 si no existe.
                // Luego, le sumamos 1 y lo guardamos en el mapa.
                recuentoCodigosPostales.put(codigoPostal, recuentoCodigosPostales.getOrDefault(codigoPostal, 0) + 1);
            }


            System.out.println("\n--- Recuento de asentamientos por código postal ---\n");
            // Iteramos sobre cada par clave-valor (Entry) del HashMap.
            for (Map.Entry<String, Integer> entry : recuentoCodigosPostales.entrySet()) {
                // Imprimimos el resultado.
                System.out.println("Código postal: " + entry.getKey() + " - Número de asentamientos: " + entry.getValue());
            }

            // Este bloque 'catch' maneja la excepción en caso de que el archivo no se encuentre o no se pueda leer.
        } catch (IOException e) {

            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}