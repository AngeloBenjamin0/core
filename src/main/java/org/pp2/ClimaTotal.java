package org.pp2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class ClimaTotal {

    private final Map<String, Dispositivo> nombreDispositivoMap = new HashMap<>();

    public ClimaTotal(String especificacionDispositivosPath){
        TypeReference<Map<String, List<String>>> typeReference = new TypeReference<>() {};
        Map<String, List<String>> especificacionDispositivos;
        try {
            especificacionDispositivos = new ObjectMapper().readValue(new File(especificacionDispositivosPath), typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Map.Entry<String, List<String>> especificacion : especificacionDispositivos.entrySet()){
            List<Class<Dispositivo>> clasesDispositivo = especificacion.getValue().stream()
                    .map(nombreClase -> new DispositivoClassFactory().crear(nombreClase))
                    .collect(Collectors.toList());

            Dispositivo dispositivo = new DispositivoFactory(especificacion.getKey(), clasesDispositivo).crear();
            nombreDispositivoMap.put(dispositivo.getNombre(), dispositivo);
        }
    }

    public void ejecutarComando(String nombreDispositivo, String nombreComando){
        if (!nombreDispositivoMap.containsKey(nombreDispositivo))
            throw new IllegalArgumentException("Dispositivo inexistente");

        nombreDispositivoMap.get(nombreDispositivo).ejecutar(nombreComando);
    }

}
