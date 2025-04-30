public class Bomba extends Elemento implements Destruible {
    private int radio;

    public Bomba(Escenario e, Posicion p, int radio) {
        super(e, p);
        this.radio = radio;
    }

    @Override
    public String destruir() {
        return "Bomba destruida";
    }

    public void explotar() {
        System.out.println("Explotando bomba!!");
        escenario.destruirElementos(posicion, radio);
    }

    @Override
    public String toString() {
        return "B";
    }
}