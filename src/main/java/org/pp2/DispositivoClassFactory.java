package org.pp2;

public class DispositivoClassFactory {

    @SuppressWarnings("unchecked")
    public Class<Dispositivo> crear(String className){
        Class<?> cls;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new DiscovererException(String.format("Clase %s no encontrada", className), e);
        }
        if (!Dispositivo.class.isAssignableFrom(cls))
            throw new RuntimeException(String.format("La clase %s no es un Dispositivo", className));

        return (Class<Dispositivo>) cls;
    }

}
