package org.pp2;

public class DefaultDriverClimatizador implements DriverClimatizador {
    @Override
    public void apagar(Dispositivo dispositivo) {
        System.out.printf("Apagando dispositivo llamado \"%s\"",
                dispositivo.getNombre());
    }

    @Override
    public void encender(Dispositivo dispositivo) {
        System.out.printf("Encendiendo dispositivo llamado \"%s\"",
                dispositivo.getNombre());
    }
}
