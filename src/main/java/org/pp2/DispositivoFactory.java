package org.pp2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class DispositivoFactory {

    private final List<Class<Dispositivo>> clasesDispositivo;
    private final String nombreDispositivo;

    public DispositivoFactory(String nombreDispositivo, List<Class<Dispositivo>> clasesDispositivo){
        // Validación de que clasesDispositivos.size() > 0
        this.nombreDispositivo = nombreDispositivo;
        this.clasesDispositivo = new ArrayList<>(clasesDispositivo);
    }

    public Dispositivo crear() throws RuntimeException {
        Dispositivo result = null;

        for (Class<Dispositivo> dispositivoClass : this.clasesDispositivo){
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
                finalDispositivo.ejecutar(comando);
            }
        };
    }
}
