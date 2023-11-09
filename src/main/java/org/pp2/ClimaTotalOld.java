package org.pp2;

import lombok.Getter;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class ClimaTotalOld {

    private final Map<String, Dispositivo> nombreDispositivoMap = new HashMap<>();

    public ClimaTotalOld(String dispositivosPath) throws FileNotFoundException {
        List<Dispositivo> dispositivosDescubiertos = new DispositivoDiscoverer().discover(dispositivosPath);
        for (Dispositivo dispositivo: dispositivosDescubiertos)
            nombreDispositivoMap.put(dispositivo.getNombre(), dispositivo);

    }

    public void ejecutarComando(String nombreDispositivo, String nombreComando){
        if (!nombreDispositivoMap.containsKey(nombreDispositivo))
            throw new IllegalArgumentException("Dispositivo inexistente");

        nombreDispositivoMap.get(nombreDispositivo).ejecutar(nombreComando);
    }

}