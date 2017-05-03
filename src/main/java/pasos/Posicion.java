package pasos;

/**
 * Created by oscar on 2/5/17.
 */
public class Posicion {
    private long x;
    private long y;

    public Posicion(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Posicion posicion = (Posicion) o;

        if (x != posicion.x) return false;
        return y == posicion.y;
    }

    @Override
    public int hashCode() {
        int result = (int) (x ^ (x >>> 32));
        result = 31 * result + (int) (y ^ (y >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                x +
                ", " + y +
                '}';
    }
}
