package org.pp2;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClimaTotal {

    public static EstablecedorTemperatura init(String dispositivosJsonConfigPath){
        List<Dispositivo> dispositivos = new DispositivoMapper(dispositivosJsonConfigPath).getDispositivos();
        Set<DriverClimatizadorFactory> driverFactories = new DriverClimatizadorFactoryDiscoverer().discover("libs/");
        Map<Dispositivo, DriverClimatizador> dispositivoDriverClimatizadorMap = new EnlazadorDispositivoDriverClimatizador(dispositivos, driverFactories).enlazar();

        return new EstablecedorTemperatura(dispositivoDriverClimatizadorMap);
    }

}
