package org.pp2;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class DriverClimatizadorFactoryDiscoverer {

    public Set<DriverClimatizadorFactory> discover(String path) throws FileNotFoundException {
        // FIXME: Tenemos que emprolijar este código. Sobre todo el manejo de excepciones
        Set<DriverClimatizadorFactory> result = new HashSet<>();
        File directory = new File(path);
        if (!directory.exists()) throw new FileNotFoundException();
        for (File f : Objects.requireNonNull(new File(path).listFiles())) {
            if (!f.getName().endsWith(".class")) continue;
            Class<?> cls;
            try {
                cls = Class.forName(f.getName().replace(".class", ""));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (!DriverClimatizadorFactory.class.isAssignableFrom(cls))
                throw new RuntimeException(); // TODO: Elegir una mejor excepción
            try {
                result.add((DriverClimatizadorFactory) cls.getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

}
