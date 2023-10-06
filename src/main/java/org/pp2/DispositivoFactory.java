package org.pp2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class DispositivoFactory {
    private final File dispositivoJsonFile;
    private final Set<DriverFactory> driverFactories;

    public DispositivoFactory(String dispositivoJsonFilePath, Set<DriverFactory> driverFactories) {
        this.dispositivoJsonFile = new File(dispositivoJsonFilePath);
        this.driverFactories = driverFactories;
    }

    public List<Dispositivo> getDispositivos(){
        List<DispositivoAdapter> dispositivos = getDispositivoAdapterList();

        Map<DispositivoAdapter, Object> dispositivoObjectMap = new HashMap<>();

        for (DispositivoAdapter dispositivoAdapter : dispositivos){
            List<Object> driversCompatibles = driverFactories.stream()
                    .filter(driverFactory -> driverFactory.isCompatible(dispositivoAdapter))
                    .map(driverFactory -> driverFactory.create(dispositivoAdapter))
                    .collect(Collectors.toList());
            if (!driversCompatibles.isEmpty())
                // Se elige el primer Driver de la lista.
                dispositivoObjectMap.put(dispositivoAdapter, driversCompatibles.get(0));
        }

        for (Map.Entry<DispositivoAdapter, Object> entry : dispositivoObjectMap.entrySet()){
            Map<String, Runnable> comandos = new HashMap<>();
            DispositivoAdapter dispositivo = entry.getKey();
            Object driver = entry.getValue();

            List<Method> methods = List.of(driver.getClass().getDeclaredMethods());
            // TODO: Agregar las siguientes validaciones: Todos los métodos tienen 0 parámetros. Solo obtener los metodos publicos
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

        return new ArrayList<>(dispositivos); // TODO: Como convertir de List<DispositivoAdapter> a List<Dispositivo>?
    }

    private List<DispositivoAdapter> getDispositivoAdapterList() {
        try {
            return new ObjectMapper().readValue(dispositivoJsonFile, new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
