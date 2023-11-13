package org.pp2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ClimaTotalFactory {
    private final EspecificacionParser especificacionParser;

    public ClimaTotalFactory(String especificacionDispositivosPath) throws FileNotFoundException {
        this.especificacionParser = new EspecificacionParser(new File(especificacionDispositivosPath));
    }

    public ClimaTotal crear() {
        Set<Dispositivo> result = new HashSet<>();
        Map<String, List<String>> nombreDispositivosMap = this.especificacionParser.parse();
        for (Map.Entry<String, List<String>> especificacion : nombreDispositivosMap.entrySet()) {
            List<Class<Dispositivo>> clasesDispositivo = especificacion.getValue().stream()
                    .map(nombreClase -> new DispositivoClassFactory().crear(nombreClase))
                    .collect(Collectors.toList());

            result.add(new DispositivoFactory().crear(especificacion.getKey(), clasesDispositivo));
        }
        return new ClimaTotal(result);
    }
}
