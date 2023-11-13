package org.pp2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class DispositivoFactory {

    public Dispositivo crear(String nombreDispositivo, List<Class<Dispositivo>> clasesDispositivo) throws RuntimeException {
        Dispositivo result = null;

        for (Class<Dispositivo> dispositivoClass : clasesDispositivo){
            if (result == null) {
                try {
                    result = dispositivoClass.getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                continue;
            }
            Constructor<Dispositivo> dispositivoConstructor;
            try {
                dispositivoConstructor = dispositivoClass.getDeclaredConstructor(Dispositivo.class);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            try {
                result = dispositivoConstructor.newInstance(result);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

        Dispositivo finalDispositivo = result;
        return new Dispositivo() {
            @Override
            public String getNombre() {
                return nombreDispositivo;
            }
            @Override
            public void ejecutar(String comando) {
                if (finalDispositivo == null) throw new IllegalArgumentException("Comando inexistente");
                finalDispositivo.ejecutar(comando);
            }
        };
    }
}
