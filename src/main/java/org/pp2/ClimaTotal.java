package org.pp2;

import org.pp2.comando.Interprete;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class ClimaTotal {

    public static List<Dispositivo> inicializarDispositivos(String dispositivosJsonPath, String driverClimatizadorFactoriesPath) throws FileNotFoundException {
        List<Dispositivo> dispositivos = new DispositivoMapper(dispositivosJsonPath).getDispositivos();

        Set<DriverClimatizadorFactory> driverClimatizadorFactories = new DriverClimatizadorFactoryDiscoverer().discover(driverClimatizadorFactoriesPath);

        // FIXME: Esto no tiene que estar hardcodeado. Vemos factible el uso de Reflection.
        Map<String, BiConsumer<DriverClimatizador, Dispositivo>> nombreComandoEjecutorMap = new HashMap<>();
        nombreComandoEjecutorMap.put("ENCENDER", DriverClimatizador::encender);
        nombreComandoEjecutorMap.put("ENFRIAR", DriverClimatizador::enfriar);

        dispositivos.forEach(dispositivo -> {
            List<DriverClimatizadorFactory> driversCompatibles = driverClimatizadorFactories.stream().filter(driver -> driver.isCompatible(dispositivo)).collect(Collectors.toList());
            if (!driversCompatibles.isEmpty())
                dispositivo.setInterprete(new Interprete(driversCompatibles.get(0).create(), nombreComandoEjecutorMap));

        });

        return dispositivos;
    }

}
