public class Extraterrestre extends Personaje {
    public Extraterrestre(String nombre, Escenario e, Posicion p) {
        super(nombre, e, p);
    }

    @Override
    public String toString() {
        return "E";
    }
}