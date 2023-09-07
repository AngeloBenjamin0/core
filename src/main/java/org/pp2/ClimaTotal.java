package org.pp2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public class ClimaTotal {

    public static EstablecedorTemperatura init(String dispositivosJsonConfigPath) throws IOException {
        List<Dispositivo> dispositivos = new ObjectMapper().readValue(
                new File(dispositivosJsonConfigPath),
                new TypeReference<>() {
                }
        );

        List<DriverClimatizadorFactory> drivers = new DriverClimatizadorFactoryDiscoverer().discover("libs/");

        DriverClimatizadorFactory driverClimatizador = drivers.get(0);
        Map<Dispositivo, DriverClimatizador> dispositivoDriverClimatizadorMap = dispositivos
                .stream()
                .collect(toMap(Function.identity(), dispositivo -> driverClimatizador.create()));

        return new EstablecedorTemperatura(dispositivoDriverClimatizadorMap);
    }

}
