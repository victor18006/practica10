import java.util.ArrayList;

public class Escenario {
    private String nombre;
    private Elemento[][] campoDeBatalla;

    public Escenario(String nombre) {
        this.nombre = nombre;
        campoDeBatalla = new Elemento[10][10]; // Escenario de 10x10
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
}