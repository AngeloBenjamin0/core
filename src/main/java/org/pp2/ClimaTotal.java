package org.pp2;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ClimaTotal {

    public static List<DispositivoAdapter> inicializarDispositivos(String dispositivosJsonPath, String driversPath) throws FileNotFoundException {
        List<DispositivoAdapter> dispositivos = new DispositivoFactory(dispositivosJsonPath).getDispositivos();
        Set<DriverFactory> driverFactories = new DriverFactoryDiscoverer().discover(driversPath);


        Map<DispositivoAdapter, Object> dispositivoObjectMap = new HashMap<>();

        for (DispositivoAdapter dispositivoAdapter : dispositivos){
            List<Object> driversCompatibles = driverFactories.stream()
                    .filter(driverFactory -> driverFactory.isCompatible(dispositivoAdapter))
                    .map(driverFactory -> driverFactory.create(dispositivoAdapter))
                    .collect(Collectors.toList());
            if (!driversCompatibles.isEmpty())
                // Nos quedamos arbitrariamente con un Driver.
                dispositivoObjectMap.put(dispositivoAdapter, driversCompatibles.get(0));
        }

        for (Map.Entry<DispositivoAdapter, Object> entry : dispositivoObjectMap.entrySet()){
            Map<String, Runnable> comandos = new HashMap<>();
            DispositivoAdapter dispositivo = entry.getKey();
            Object driver = entry.getValue();

            List<Method> methods = List.of(driverFactories.getClass().getDeclaredMethods());
            // TODO: Agregar las siguientes validaciones: Todos los métodos tienen 0 parámetros.
            for (Method method : methods) {
                String nombreComando = method.getName().toUpperCase();
                comandos.put(nombreComando, () -> {
                    try {
                        method.invoke(driver);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            dispositivo.setComandos(comandos);
        }

        return dispositivos;
    }

}
