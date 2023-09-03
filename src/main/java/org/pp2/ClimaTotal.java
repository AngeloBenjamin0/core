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

    public static EstablecedorTemperatura init(String dispositivosConfigPath) throws IOException {
        List<Dispositivo> dispositivos = new ObjectMapper().readValue(
                new File(dispositivosConfigPath),
                new TypeReference<>() {
                }
        );

        Map<Dispositivo, IntegracionClimatizadores> dispositivoIntegracionClimatizadoresMap = dispositivos
                .stream()
                .collect(toMap(Function.identity(), dispositivo -> new DefaultIntegracionClimatizadores()));

        return new EstablecedorTemperatura(dispositivoIntegracionClimatizadoresMap);
    }

}