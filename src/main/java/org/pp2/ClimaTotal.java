package org.pp2;

import lombok.Getter;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class ClimaTotal {

    private final Map<String, Dispositivo> nombreDispositivoMap = new HashMap<>();

    public ClimaTotal(String dispositivosPath, String dispositivoProxyFactoriesPath) throws FileNotFoundException {
        List<Dispositivo> dispositivosDescubiertos = new DispositivoDiscoverer().discover(dispositivosPath);
        List<DispositivoProxyFactory> dispositivoProxyFactoriesDescubiertos = new DispositivoProxyFactoryDiscoverer().discover(dispositivoProxyFactoriesPath);

        for (Dispositivo dispositivo: dispositivosDescubiertos) {
            Dispositivo candidato = dispositivo;
            for (DispositivoProxyFactory proxyFactory: dispositivoProxyFactoriesDescubiertos){
                if (proxyFactory.esCandidato(dispositivo))
                    candidato = proxyFactory.crear(candidato);
            }
            nombreDispositivoMap.put(candidato.getNombre(), candidato);
        }
    }

    public void ejecutarComando(String nombreDispositivo, String nombreComando){
        if (!nombreDispositivoMap.containsKey(nombreDispositivo))
            throw new IllegalArgumentException("Dispositivo inexistente");

        nombreDispositivoMap.get(nombreDispositivo).ejecutar(nombreComando);
    }

}
