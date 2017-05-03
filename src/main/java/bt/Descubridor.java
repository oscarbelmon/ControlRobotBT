package bt;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import java.io.IOException;

/**
 * Created by oscar on 3/5/17.
 */
public class Descubridor implements DiscoveryListener {
    private Object cerrojo;

    public Descubridor(Object cerrojo) {
        this.cerrojo = cerrojo;
    }

    @Override
    public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass deviceClass) {
        try {
            System.out.println(remoteDevice.getFriendlyName(true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void servicesDiscovered(int i, ServiceRecord[] serviceRecords) {

    }

    @Override
    public void serviceSearchCompleted(int i, int i1) {
        System.out.println("Acabó la búsqueda de servicios.");
    }

    @Override
    public void inquiryCompleted(int i) {
        System.out.println("Acabó el descubrimiento");
        synchronized (cerrojo) {
            cerrojo.notify();
        }
    }
}
