package org.pp2;

import lombok.Getter;

import java.io.FileNotFoundException;
import java.util.List;

@Getter
public class ClimaTotal {

    private final List<Dispositivo> dispositivos;

    public ClimaTotal(String dispositivosPath) throws FileNotFoundException {
        dispositivos = new DispositivoDiscoverer().discover(dispositivosPath);
    }

}
