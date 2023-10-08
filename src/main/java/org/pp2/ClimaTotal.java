package org.pp2;

import java.io.FileNotFoundException;
import java.util.List;

public class ClimaTotal {

    public static List<Dispositivo> inicializarDispositivos(String driversPath) throws FileNotFoundException {
        return new DispositivoDiscoverer().discover(driversPath);
    }

}
