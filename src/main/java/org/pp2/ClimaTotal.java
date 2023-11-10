package org.pp2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class ClimaTotal {

    private final Map<String, Dispositivo> nombreDispositivoMap = new HashMap<>();

    public ClimaTotal(String especificacionDispositivosPath) throws FileNotFoundException {
        TypeReference<Map<String, List<String>>> typeReference = new TypeReference<>() {};
        Map<String, List<String>> especificacionDispositivos;
        File especificacionDispositivo =new File(especificacionDispositivosPath);
        if (!especificacionDispositivo.exists()) throw new FileNotFoundException();
        try {
            especificacionDispositivos = new ObjectMapper().readValue(especificacionDispositivo, typeReference);
        } catch (MismatchedInputException e){
            throw new RuntimeException("Formato de especificación inválido", e);
        }
        catch (IOException e) {
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
