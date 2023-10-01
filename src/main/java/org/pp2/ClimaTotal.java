package org.pp2;

import org.pp2.comando.Interprete;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class ClimaTotal {

    public static List<Dispositivo> inicializarDispositivos(String dispositivosJsonPath, String comandoDispositivoFactoriesPath) throws FileNotFoundException {
        List<Dispositivo> dispositivos = new DispositivoFactory(dispositivosJsonPath).getDispositivos();

        Set<ComandoDispositivoFactory> comandoDispositivoFactories = new ComandoDispositivoFactoryDiscoverer().discover(comandoDispositivoFactoriesPath);

        for (Dispositivo dispositivo : dispositivos) {
            List<ComandoDispositivoFactory> comandosAceptados = comandoDispositivoFactories.stream().filter(driver -> driver.isCompatible(dispositivo)).collect(Collectors.toList());
            Map<String, ComandoDispositivo> nombreComandoDispositivoMap = comandosAceptados
                    .stream()
                    .map(ComandoDispositivoFactory::create)
                    .collect(toMap(ComandoDispositivo::getNombreComando, Function.identity()));
            dispositivo.setInterprete(new Interprete(nombreComandoDispositivoMap));
        }

        return dispositivos;
    }

}
