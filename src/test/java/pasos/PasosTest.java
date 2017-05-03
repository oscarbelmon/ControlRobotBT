package pasos;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

/**
 * Created by oscar on 2/5/17.
 */
public class PasosTest {
    @Test
    public void primer() {
        Orientacion orientacionInicial = Orientacion.N;
        Orientacion orientacionFinal = Orientacion.S;
        Posicion posicionInicial = new Posicion(5,1);
        Posicion posicionFinal = new Posicion(7,1);
        Estado estadoInicial = new Estado(orientacionInicial, posicionInicial);
        Estado estadoFinal = new Estado(orientacionFinal, posicionFinal);
        Acciones acciones = new Acciones("AAADAADAAA");
        assertThat(acciones.ejecutaAcciones(estadoInicial), is(estadoFinal));
    }

    @Test
    public void segundo() {
        Estado estadoInicial = new Estado(Orientacion.N, new Posicion(1, 1));
        Acciones acciones = new Acciones("AAADAA");
        assertThat(acciones.ejecutaAcciones(estadoInicial), is(new Estado(Orientacion.E, new Posicion(3, 4))));
    }

    @Test
    public void tercero() {
        Estado estadoInicial = new Estado(Orientacion.O, new Posicion(1, 8));
        Acciones acciones = new Acciones("DDAADAAA");
        assertThat(acciones.ejecutaAcciones(estadoInicial), is(new Estado(Orientacion.S, new Posicion(3,5))));
    }

    @Test
    public void cuarto() {
        Estado estado = new Estado(Orientacion.N, new Posicion(1, 1));
        Acciones acciones = new Acciones("DD");
        assertThat(acciones.ejecutaAcciones(estado), is(new Estado(Orientacion.S, new Posicion(1, 1))));
    }

    @Test
    public void quinto() {
        Estado estado = new Estado(Orientacion.S, new Posicion(5, 5));
        Acciones acciones = new Acciones("AAI");
        assertThat(acciones.ejecutaAcciones(estado), is(new Estado(Orientacion.E, new Posicion(5, 3))));
    }

    @Test
    public void vueltaPrimero() {
        Estado estado = new Estado(Orientacion.S, new Posicion(5, 5));
        Acciones acciones = new Acciones("AAI");
        Estado estadoFinal = acciones.ejecutaAcciones(estado);
        Acciones vuelta = acciones.vuelta(estadoFinal, estado);
        assertThat(vuelta.ejecutaAcciones(estadoFinal), is(estado));
    }

    @Test
    public void vueltaSegundo() {
        Estado estado = new Estado(Orientacion.N, new Posicion(5, 1));
        Acciones acciones = new Acciones("AAADAADAA");
        Estado estadoFinal = acciones.ejecutaAcciones(estado);
        System.out.println(estadoFinal);
        Acciones vuelta = acciones.vuelta(estadoFinal, estado);
        System.out.println(vuelta.getAcciones());
        assertThat(vuelta.ejecutaAcciones(estadoFinal), is(estado));
    }

    @Test
    public void vueltaTercero() {
        Estado estadoInicial = new Estado(Orientacion.N, new Posicion(1, 1));
        Acciones acciones = new Acciones("AAAAAAADAAAAAAA");
        Estado estadoFinal = acciones.ejecutaAcciones(estadoInicial);
        Acciones vuelta = acciones.vuelta(estadoFinal, estadoInicial);
        System.out.println(vuelta.getAcciones());
        assertThat(vuelta.ejecutaAcciones(estadoFinal), is(estadoInicial));
    }

    @Test
    public void vueltaCuarto() {
        Estado estadoInicial = new Estado(Orientacion.N, new Posicion(1, 1));
        Acciones acciones = new Acciones("ADAIADAIADAIADAIADA");
        Estado estadoFinal = acciones.ejecutaAcciones(estadoInicial);
        Acciones vuelta = acciones.vuelta(estadoFinal, estadoInicial);
        System.out.println(vuelta.getAcciones());
        assertThat(vuelta.ejecutaAcciones(estadoFinal), is(estadoInicial));
    }
}
