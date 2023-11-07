package org.pp2;

import java.util.List;
import java.util.stream.Collectors;

public class DispositivoProxyFactorySelector {
    private final List<DispositivoProxyFactory> dispositivoProxyFactories;

    public DispositivoProxyFactorySelector(List<DispositivoProxyFactory> dispositivoProxyFactories) {
        this.dispositivoProxyFactories = dispositivoProxyFactories;
    }

    public List<DispositivoProxyFactory> selectCompatibles(Dispositivo dispositivo){
            return dispositivoProxyFactories.stream()
                    .filter(dispositivoProxyFactory -> dispositivoProxyFactory.esCandidato(dispositivo))
                    .collect(Collectors.toList());
    }
}
