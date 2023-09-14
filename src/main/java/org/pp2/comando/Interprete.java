package org.pp2.comando;

import org.pp2.Dispositivo;
import org.pp2.DriverClimatizador;

import java.util.Map;
import java.util.function.BiConsumer;

public class Interprete {
    private final Map<String, BiConsumer<DriverClimatizador, Dispositivo>> nombreComandoEjecutorMap;
    private final DriverClimatizador driverClimatizador;

    public Interprete(DriverClimatizador driverClimatizador,Map<String, BiConsumer<DriverClimatizador, Dispositivo>> nombreComandoEjecutorMap) {
        this.driverClimatizador = driverClimatizador;
        this.nombreComandoEjecutorMap = nombreComandoEjecutorMap;
    }

    public void ejecutar(Dispositivo dispositivo, String comando) {
        BiConsumer<DriverClimatizador, Dispositivo> ejecutorComando = nombreComandoEjecutorMap.get(comando);
        if (ejecutorComando == null)
            throw new IllegalArgumentException("Comando no soportado");

        ejecutorComando.accept(driverClimatizador, dispositivo);
    }
}
