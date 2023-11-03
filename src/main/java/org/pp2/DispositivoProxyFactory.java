package org.pp2;

public interface DispositivoProxyFactory {

    Dispositivo crear(Dispositivo dispositivo);

    boolean esCandidato(Dispositivo dispositivo);

}
