package org.pp2;

public class DefaultIntegracionClimatizador implements IntegracionClimatizador {
    @Override
    public void establecerTemperatura(Dispositivo dispositivo, int temperatura) {
        System.out.printf("Estableciendo temperatura del dispositivo llamado \"%s\" modelo \"%s\" a %s grados%n",
                dispositivo.getNombre(), dispositivo.getModelo(), temperatura);
    }
}
