package org.pp2;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
public class ClimaTotal {

    private final Map<String, Dispositivo> nombreDispositivoMap = new HashMap<>();

    public ClimaTotal(Set<Dispositivo> dispositivos){
        for (Dispositivo dispositivo: dispositivos)
            nombreDispositivoMap.put(dispositivo.getNombre(), dispositivo);
    }

    public void ejecutarComando(String nombreDispositivo, String nombreComando){
        if (!nombreDispositivoMap.containsKey(nombreDispositivo))
            throw new IllegalArgumentException("Dispositivo inexistente");

        nombreDispositivoMap.get(nombreDispositivo).ejecutar(nombreComando);
    }

}
