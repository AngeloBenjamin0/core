package org.pp2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public class ClimaTotal {

    public static EstablecedorTemperatura init(String dispositivosConfigPath) throws IOException {
        List<Dispositivo> dispositivos = new ObjectMapper().readValue(
                new File(dispositivosConfigPath),
                new TypeReference<>() {
                }
        );

        List<IntegracionClimatizador> integraciones;
        try {
            integraciones = new IntegracionClimatizadorDiscoverer().discover("libs/");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        IntegracionClimatizador integracionClimatizador = integraciones.get(0);
        Map<Dispositivo, IntegracionClimatizador> dispositivoIntegracionClimatizadoresMap = dispositivos
                .stream()
                .collect(toMap(Function.identity(), dispositivo -> integracionClimatizador));

        return new EstablecedorTemperatura(dispositivoIntegracionClimatizadoresMap);
    }

}
