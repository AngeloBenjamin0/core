package org.pp2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ClimaTotalFactory {

    private final static TypeReference<Map<String, List<String>>> TYPE_REFERENCE = new TypeReference<>() {};
    private final File especificacionDispositivos;

    public ClimaTotalFactory(String especificacionDispositivosPath) throws FileNotFoundException {
        this.especificacionDispositivos = new File(especificacionDispositivosPath);
        if (!this.especificacionDispositivos.exists())
            throw new FileNotFoundException();
    }

    public ClimaTotal crear() {
        Set<Dispositivo> result = new HashSet<>();
        Map<String, List<String>> nombreDispositivosMap;
        try {
            nombreDispositivosMap = new ObjectMapper().readValue(especificacionDispositivos, TYPE_REFERENCE);
        } catch (MismatchedInputException e) {
            throw new RuntimeException("Formato de especificación inválido", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Map.Entry<String, List<String>> especificacion : nombreDispositivosMap.entrySet()) {
            List<Class<Dispositivo>> clasesDispositivo = especificacion.getValue().stream()
                    .map(nombreClase -> new DispositivoClassFactory().crear(nombreClase))
                    .collect(Collectors.toList());

            result.add(new DispositivoFactory(especificacion.getKey(), clasesDispositivo).crear());
        }
        return new ClimaTotal(result);
    }
}
