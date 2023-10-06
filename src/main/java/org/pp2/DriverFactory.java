package org.pp2;

public interface DriverFactory {
    boolean isCompatible(Dispositivo dispositivo);
    Object create(Dispositivo dispositivo);

}
