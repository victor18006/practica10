import java.util.ArrayList;
import java.io.*;

public class Escenario {
    private String nombre;
    private Elemento[][] campoDeBatalla;

    public Escenario(String nombre) {
        this.nombre = nombre;
        campoDeBatalla = new Elemento[10][10];
    }

    public void addElemento(Elemento e) {
        Posicion p = e.getPosicion();
        campoDeBatalla[p.getRenglon()][p.getColumna()] = e;
    }

    public void destruirElementos(Posicion centro, int radio) {
        ArrayList<Elemento> enZona = new ArrayList<>();

        for (int i = centro.getRenglon() - radio; i <= centro.getRenglon() + radio; i++) {
            for (int j = centro.getColumna() - radio; j <= centro.getColumna() + radio; j++) {
                if (i >= 0 && i < 10 && j >= 0 && j < 10) {
                    Elemento e = campoDeBatalla[i][j];
                    if (e != null) {
                        enZona.add(e);
                    }
                }
            }
        }

        for (Elemento e : enZona) {
            if (e instanceof Destruible) {
                System.out.println(((Destruible) e).destruir());
                Posicion p = e.getPosicion();
                campoDeBatalla[p.getRenglon()][p.getColumna()] = null;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Escenario: ").append(nombre).append("\n");
        for (int i = 0; i < campoDeBatalla.length; i++) {
            for (int j = 0; j < campoDeBatalla[i].length; j++) {
                sb.append(campoDeBatalla[i][j] == null ? "0 " : campoDeBatalla[i][j].toString() + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public Elemento getElementoEn(int renglon, int columna) {
        if (renglon >= 0 && renglon < 10 && columna >= 0 && columna < 10) {
            return campoDeBatalla[renglon][columna];
        }
        return null;
    }

    public void cargarDesdeArchivo(String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(" ");
                String tipo = partes[0];
                int ren = Integer.parseInt(partes[1]);
                int col = Integer.parseInt(partes[2]);
                Posicion pos = new Posicion(ren, col);

                switch (tipo) {
                    case "Roca":
                        addElemento(new Roca(this, pos));
                        break;
                    case "Terricola":
                        addElemento(new Terricola("T", this, pos));
                        break;
                    case "Extraterrestre":
                        addElemento(new Extraterrestre("E", this, pos));
                        break;
                    case "Bomba":
                        int radio = Integer.parseInt(partes[3]);
                        addElemento(new Bomba(this, pos, radio));
                        break;
                    default:
                        System.out.println("Tipo no reconocido: " + tipo);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public void guardarEnArchivo(String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (int i = 0; i < campoDeBatalla.length; i++) {
                for (int j = 0; j < campoDeBatalla[i].length; j++) {
                    Elemento e = campoDeBatalla[i][j];
                    if (e != null) {
                        if (e instanceof Roca) {
                            writer.write("Roca " + i + " " + j);
                        } else if (e instanceof Terricola) {
                            writer.write("Terricola " + i + " " + j);
                        } else if (e instanceof Extraterrestre) {
                            writer.write("Extraterrestre " + i + " " + j);
                        } else if (e instanceof Bomba) {
                            Bomba b = (Bomba) e;
                            writer.write("Bomba " + i + " " + j + " " + b.getRadio());
                        }
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }    
}