package pasos;

/**
 * Created by oscar on 2/5/17.
 */
public class Estado {
    private Orientacion orientacion;
    private Posicion posicion;

    public Estado(Orientacion orientacion, Posicion posicion) {
        this.orientacion = orientacion;
        this.posicion = posicion;
    }

    public Orientacion getOrientacion() {
        return orientacion;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Estado estado = (Estado) o;

        if (orientacion != estado.orientacion) return false;
        return posicion != null ? posicion.equals(estado.posicion) : estado.posicion == null;
    }

    @Override
    public int hashCode() {
        int result = orientacion != null ? orientacion.hashCode() : 0;
        result = 31 * result + (posicion != null ? posicion.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                orientacion +
                ", " + posicion +
                '}';
    }
}
