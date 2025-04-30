public abstract class Personaje extends Elemento implements Destruible {
    protected String nombre;
    protected int puntosDeVida;

    public Personaje(String nombre, Escenario e, Posicion p) {
        super(e, p);
        this.nombre = nombre;
        this.puntosDeVida = 100; // Valor inicial arbitrario
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String destruir() {
        return nombre + " destruido";
    }
}