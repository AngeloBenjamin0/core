package org.pp2;

public interface DriverClimatizadorFactory {

    boolean isCompatible(Dispositivo dispositivo);
    DriverClimatizador create();

}
