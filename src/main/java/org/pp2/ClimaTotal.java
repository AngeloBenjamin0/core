package org.pp2;

import org.interfaces.ComandoDispositivo;
import org.interfaces.ComandoDispositivoFactory;
import org.pp2.comando.Interprete;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class ClimaTotal {

    public static List<DispositivoConcreto> inicializarDispositivos(String dispositivosJsonPath, String comandoDispositivoFactoriesPath) throws FileNotFoundException {
        List<DispositivoConcreto> dispositivoConcretos = new DispositivoFactory(dispositivosJsonPath).getDispositivos();

        Set<ComandoDispositivoFactory> comandoDispositivoFactories = new ComandoDispositivoFactoryDiscoverer().discover(comandoDispositivoFactoriesPath);

        for (DispositivoConcreto dispositivoConcreto : dispositivoConcretos) {
            List<ComandoDispositivoFactory> comandosAceptados = comandoDispositivoFactories.stream().filter(driver -> driver.isCompatible(dispositivoConcreto)).collect(Collectors.toList());
            Map<String, ComandoDispositivo> nombreComandoDispositivoMap = comandosAceptados
                    .stream()
                    .map(ComandoDispositivoFactory::create)
                    .collect(toMap(ComandoDispositivo::getNombreComando, Function.identity()));
            dispositivoConcreto.setInterprete(new Interprete(nombreComandoDispositivoMap));
        }

        return dispositivoConcretos;
    }

}
