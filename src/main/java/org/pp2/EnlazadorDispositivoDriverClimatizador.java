package org.pp2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

// TODO: No debe ser muy difícil encontrar un nombre mejor que este
public class EnlazadorDispositivoDriverClimatizador {

    private final List<Dispositivo> dispositivos;
    private final Set<DriverClimatizadorFactory> driverClimatizadorFactories;


    public EnlazadorDispositivoDriverClimatizador(List<Dispositivo> dispositivos, Set<DriverClimatizadorFactory> driverClimatizadorFactories) {
        this.dispositivos = dispositivos;
        this.driverClimatizadorFactories = driverClimatizadorFactories;
    }

    public Map<Dispositivo, DriverClimatizador> enlazar(){
        Map<Dispositivo, DriverClimatizador>  dispositivoDriverClimatizadorMap = new HashMap<>();

        dispositivos.forEach(dispositivo -> {
            List<DriverClimatizadorFactory> driversCompatibles = driverClimatizadorFactories.stream().filter(driver -> driver.isCompatible(dispositivo)).collect(Collectors.toList());
            if (!driversCompatibles.isEmpty())
                // En caso de haber más de un driver compatible con un dispositivo, entonces tomamos el primero.
                dispositivoDriverClimatizadorMap.put(dispositivo, driversCompatibles.get(0).create());
        });

        return dispositivoDriverClimatizadorMap;
    }

}
