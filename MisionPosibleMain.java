import java.util.Scanner;

public class MisionPosibleMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Escenario escenario = new Escenario("Nostromo");

        String archivo = "configuracion.txt";
        escenario.cargarDesdeArchivo(archivo);

        System.out.println("Estado inicial del escenario:");
        System.out.println(escenario);

        System.out.print("Ingrese la posición de la bomba a detonar (renglon-espacio-columna): ");
        int r = sc.nextInt();
        int c = sc.nextInt();

        sc.close();

        Elemento elegido = escenario.getElementoEn(r, c);
        if (elegido instanceof Bomba) {
            ((Bomba) elegido).explotar();
        } else {
            System.out.println("No hay una bomba en esa posición.");
        }

        System.out.println("Estado del escenario después de la detonación:");
        System.out.println(escenario);

        escenario.guardarEnArchivo(archivo);
        System.out.println("Configuración final guardada en el archivo.");
    }
}
