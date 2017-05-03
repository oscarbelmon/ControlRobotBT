package pasos;


/**
 * Created by oscar on 2/5/17.
 */
public class Acciones {
    private String acciones;

    public Acciones(String acciones) {
        super();
        this.acciones = acciones;
    }

    public String getAcciones() {
        return acciones;
    }

    public Estado ejecutaAcciones(final Estado estado) {
        String representacion = representacionCardinal(estado.getOrientacion());
        Orientacion orientacion = orientacionCardinal(estado.getOrientacion());
        Posicion posicion = posicionFinal(representacion, estado.getPosicion());
        return new Estado(orientacion, posicion);
    }

    public Acciones concatenaAcciones(Acciones acciones) {
        return new Acciones(this.acciones + acciones.acciones);
    }

    private String representacionCardinal(Orientacion orientacion) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < acciones.length(); i++) {
            switch (acciones.charAt(i)) {
                case 'A':
                    sb.append(orientacion);
                    break;
                case 'I':
                    orientacion = izquierda(orientacion);
                    break;
                case 'D':
                    orientacion = derecha(orientacion);
                    break;
            }
        }

        return sb.toString();
    }

    private Orientacion orientacionCardinal(Orientacion orientacion) {
        for(int i = 0; i < acciones.length(); i++) {
            switch (acciones.charAt(i)) {
                case 'I':
                    orientacion = izquierda(orientacion);
                    break;
                case 'D':
                    orientacion = derecha(orientacion);
                    break;
            }
        }

        return orientacion;
    }

    private Posicion posicionFinal(String representacion, Posicion posicionInicial) {
        long nortes = cuentaNortes(representacion);
        long sures = cuentaSures(representacion);
        long estes = cuentaEstes(representacion);
        long oestes = cuentaOestes(representacion);
        Posicion posicionFinal = new Posicion(posicionInicial.getX() + (estes - oestes), posicionInicial.getY() + (nortes - sures));

        return  posicionFinal;
    }

    private Orientacion derecha(Orientacion orientacion) {
        int cnt = (orientacion.ordinal() + 1) % 4;
        return Orientacion.values()[cnt];
    }

    private Orientacion izquierda(Orientacion orientacion) {
        int cnt =  (orientacion.ordinal() - 1) % 4;
        if(cnt < 0) cnt = 4 + cnt;
        return Orientacion.values()[cnt];
    }

    public Acciones vuelta(final Estado estadoIinicial, final Estado estadoFinal) {
        Orientacion orientacion = estadoIinicial.getOrientacion();
        StringBuilder sb = new StringBuilder();
        Posicion posicionInicial = estadoIinicial.getPosicion();
        Posicion posicionFinal = estadoFinal.getPosicion();

        sb.append(vueltaHorizontal(orientacion, posicionInicial.getX(), posicionFinal.getX()));
        orientacion = orientacionHorizontal(posicionInicial.getX(), posicionFinal.getX());

        sb.append(vueltaVertical(orientacion, posicionInicial.getY(), posicionFinal.getY()));
        orientacion = orientacionVertical(posicionInicial.getY(), posicionFinal.getY());

        sb.append(giros(estadoFinal.getOrientacion(), orientacion));

        return new Acciones(sb.toString());
    }

    private String vueltaHorizontal(final Orientacion orientacion, long xInicial, long xFinal) {
        StringBuilder sb = new StringBuilder();
        long x = xFinal - xInicial;
        if(x > 0) sb.append(giros(orientacion, Orientacion.E));
        else sb.append(giros(orientacion, Orientacion.O));
        sb.append(pasos(x));

        return sb.toString();
    }

    private String vueltaVertical(final Orientacion orientacion, long yInicial, long yFinal) {
        StringBuilder sb = new StringBuilder();
        long y = yFinal - yInicial;
        if(y > 0) sb.append(giros(orientacion, Orientacion.N));
        else sb.append(giros(orientacion, Orientacion.S));
        sb.append(pasos(y));

        return sb.toString();
    }

    private Orientacion orientacionHorizontal(long xInicial, long xFinal) {
        if(xFinal > xInicial) return Orientacion.E;
        else return Orientacion.O;
    }

    private Orientacion orientacionVertical(long yInicial, long yFinal) {
        if(yFinal > yInicial) return Orientacion.N;
        else return Orientacion.S;
    }

    private String pasos(long x) {
        StringBuilder sb = new StringBuilder();
        for(long i = 0; i < Math.abs(x); i++)
            sb.append("A");
        return sb.toString();
    }

    private String giros(final Orientacion orientacionInicial, final Orientacion orientacionFinal) {
        int cnt = orientacionFinal.ordinal() - orientacionInicial.ordinal();
        if(cnt == 1) return "D";
        if(cnt == 2) return "DD";
        if(cnt == 3) return "I";
        if(cnt == -1) return "I";
        if(cnt == -2) return "II";
        if(cnt == -3) return "D";
        return "";
    }

    private long cuentaNortes(String representacion) {
        return cuentaCaracteres(representacion, 'N');
    }

    private long cuentaSures(String representacion) {
        return cuentaCaracteres(representacion, 'S');
    }

    private long cuentaEstes(String representacion) {
        return cuentaCaracteres(representacion, 'E');
    }

    private long cuentaOestes(String representacion) {
        return cuentaCaracteres(representacion, 'O');
    }

    private long cuentaCaracteres(String representacion, char caracter) {
        long cnt = 0;
        for(int i = 0; i < representacion.length(); i++)
            if(caracter == representacion.charAt(i)) cnt++;

        return cnt;
    }
}
