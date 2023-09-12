package org.pp2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClimaTotal {

    public static List<Dispositivo> inicializarDispositivos(String dispositivosJsonPath, String comandosValidosJsonPath){
        List<Dispositivo> dispositivos = new DispositivoMapper(dispositivosJsonPath).getDispositivos();
        Set<String> comandosValidos;
        try {
            comandosValidos =  new ObjectMapper().readValue(new File(comandosValidosJsonPath), new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Set<DriverClimatizadorFactory> driverClimatizadorFactories = new DriverClimatizadorFactoryDiscoverer().discover("libs/");

        dispositivos.forEach(dispositivo -> {
            List<DriverClimatizadorFactory> driversCompatibles = driverClimatizadorFactories.stream().filter(driver -> driver.isCompatible(dispositivo)).collect(Collectors.toList());
            if (!driversCompatibles.isEmpty()) {
//                dispositivo.setComandosAceptables(comandosValidos);
                // En caso de haber más de un driver compatible con un dispositivo, entonces tomamos el primero.
//                dispositivo.setDriverClimatizador(driversCompatibles.get(0).create());
//                dispositivo.setDriverClimatizador(new DefaultDriverClimatizador());
            }
        });

        return dispositivos;
    }

}
