package org.pp2;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

public class ClimaTotal {

    public static List<Dispositivo> inicializarDispositivos(String dispositivosJsonPath, String driversPath) throws FileNotFoundException {
        Set<DriverFactory> driverFactories = new DriverFactoryDiscoverer().discover(driversPath);
        return new DispositivoFactory(dispositivosJsonPath, driverFactories).getDispositivos();
    }

}
