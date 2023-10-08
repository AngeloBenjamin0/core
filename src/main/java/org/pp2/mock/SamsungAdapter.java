package org.pp2.mock;

import org.pp2.Dispositivo;

public class SamsungAdapter implements Dispositivo {

    AireAcondicionadoSamsungMock aireAcondicionadoSamsungMock;

    public SamsungAdapter(){
        aireAcondicionadoSamsungMock = new AireAcondicionadoSamsungMock();
    }

    @Override
    public String getNombre() {
        return "Samsung v2";
    }

    @Override
    public void ejecutar(String comando) {
        switch (comando){
            case "APAGAR": aireAcondicionadoSamsungMock.apagar();
            case "ENCENDER": aireAcondicionadoSamsungMock.encender();
            default: throw new IllegalArgumentException("Comando no soportado");
        }
    }
}
