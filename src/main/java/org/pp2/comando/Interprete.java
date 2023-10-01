package org.pp2.comando;

import org.interfaces.ComandoDispositivo;

import java.util.Map;

public class Interprete {
    private final Map<String, ComandoDispositivo> nombreComandoDispositivoMap;

    public Interprete(Map<String,ComandoDispositivo> nombreComandoDispositivoMap) {
        this.nombreComandoDispositivoMap = nombreComandoDispositivoMap;
    }

    public void interpretar(String comando) {
        ComandoDispositivo comandoDispositivo = nombreComandoDispositivoMap.get(comando);
        if (comandoDispositivo == null)
            throw new IllegalArgumentException("Comando no soportado");

        comandoDispositivo.ejecutar();
    }
}
