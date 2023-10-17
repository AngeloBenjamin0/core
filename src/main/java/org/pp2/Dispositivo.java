package org.pp2;

public interface Dispositivo {

    String getNombre();
    void ejecutar(String comando);
    void registrarObserver(DispositivoObserver observer);
    void eliminarObserver();
}
