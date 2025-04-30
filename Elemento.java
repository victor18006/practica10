public abstract class Elemento {
    protected Posicion posicion;
    protected Escenario escenario;

    public Elemento(Escenario e, Posicion p) {
        this.escenario = e;
        this.posicion = p;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion p) {
        this.posicion = p;
    }

    public abstract String toString();
}