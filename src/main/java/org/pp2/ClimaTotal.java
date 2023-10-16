package org.pp2;

import lombok.Getter;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ClimaTotal {

    private final List<Dispositivo> dispositivos = new ArrayList<>();

    public ClimaTotal(String dispositivosPath, String configuracionHorariaPath) throws FileNotFoundException {
        List<Dispositivo> dispositivosDescubiertos = new DispositivoDiscoverer().discover(dispositivosPath);
        for (Dispositivo dispositivo: dispositivosDescubiertos)
            dispositivos.add(new DispositivoProxyFactory(configuracionHorariaPath).crear(dispositivo));
    }

}
