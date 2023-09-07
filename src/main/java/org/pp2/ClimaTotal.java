package org.pp2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ClimaTotal {

    public static EstablecedorTemperatura init(String dispositivosJsonConfigPath) throws IOException {
        // TODO: Refactorizar a una clase aparte.
        List<Dispositivo> dispositivos = new ObjectMapper().readValue(
                new File(dispositivosJsonConfigPath),
                new TypeReference<>() {
                }
        );

        Set<DriverClimatizadorFactory> drivers = new DriverClimatizadorFactoryDiscoverer().discover("libs/");

        // TODO: Refactorizar en una clase aparte
        Map<Dispositivo, DriverClimatizador> dispositivoDriverClimatizadorMap = new HashMap<>();
        dispositivos.forEach(dispositivo -> {
            List<DriverClimatizadorFactory> driversCompatibles = drivers.stream().filter(driver -> driver.isCompatible(dispositivo)).collect(Collectors.toList());
            if (!driversCompatibles.isEmpty())
                // En caso de haber más de un driver compatible con un dispositivo, entonces tomamos el primero.
                dispositivoDriverClimatizadorMap.put(dispositivo, driversCompatibles.get(0).create());
        });

        return new EstablecedorTemperatura(dispositivoDriverClimatizadorMap);
    }

}
