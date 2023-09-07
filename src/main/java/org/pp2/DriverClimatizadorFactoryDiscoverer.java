package org.pp2;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class DriverClimatizadorFactoryDiscoverer {

    public List<DriverClimatizadorFactory> discover(String path) {
        List<DriverClimatizadorFactory> result = new ArrayList<>();
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
