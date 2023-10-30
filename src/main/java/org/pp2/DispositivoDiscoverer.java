package org.pp2;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DispositivoDiscoverer {

    public List<Dispositivo> discover(String path) throws FileNotFoundException {
        // FIXME: Tenemos que emprolijar este código. Sobre todo el manejo de excepciones
        List<Dispositivo> drivers = new ArrayList<>();
        File directory = new File(path);
        if (!directory.exists()) throw new FileNotFoundException();
        for (File f : Objects.requireNonNull(new File(path).listFiles())) {
            String nombreArchivo = f.getName();
            if (!nombreArchivo.endsWith(".class")) continue;
            Class<?> cls;
            try {
                cls = Class.forName(nombreArchivo.replace(".class", ""));
            } catch (ClassNotFoundException e) {
                throw new DispositivoDiscoveringException(nombreArchivo, e);
            }
            if (!Dispositivo.class.isAssignableFrom(cls))
                throw new RuntimeException(); // TODO: Elegir una mejor excepción
            try {
                drivers.add((Dispositivo) cls.getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return drivers;
    }

}
