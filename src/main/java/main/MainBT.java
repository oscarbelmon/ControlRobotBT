package main;

import bt.Descubridor;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;

/**
 * Created by oscar on 3/5/17.
 */
public class MainBT {
//    static {
//        System.load("/Users/oscar/Oscar/Oscar/Personal/TallerresManel/PasosRobot/src/main/resources/libbluecove.jnilib");
//    }

    public static void main(String[] args) {
        new MainBT().ejecuta();
    }

    private void ejecuta() {
        boolean comenzado;
        try {
            final Object busca = new Object();
            synchronized (busca) {
                comenzado = LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC, new Descubridor(busca));
                if (comenzado) {
                    System.out.println("Está buscando");
                    busca.wait();
                }
            }
        } catch (BluetoothStateException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
