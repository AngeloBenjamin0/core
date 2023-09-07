package org.pp2;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

// FIXME: Clase solcito
public class EstablecedorTemperatura {

    private final String PROPERTY_NAME = "temperatura";
    private final PropertyChangeSupport propertyChangeSupport;
    private final Map<String, Dispositivo> idDispositivosMap;
    private final Map<Dispositivo, DriverClimatizador> dispositivoDriverClimatizadorMap;

    public EstablecedorTemperatura(Map<Dispositivo, DriverClimatizador> dispositivoDriverClimatizadorMap) {
        this.dispositivoDriverClimatizadorMap = dispositivoDriverClimatizadorMap;
        this.idDispositivosMap = dispositivoDriverClimatizadorMap.keySet().stream().collect(toMap(Dispositivo::getId, Function.identity()));
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public int establecer(String dispositivoId, int temperatura){
        Dispositivo dispositivo = idDispositivosMap.get(dispositivoId);
        if (dispositivo == null) throw new IllegalArgumentException(String.format("Dispositivo con id %s no encontrado", dispositivoId));

        dispositivoDriverClimatizadorMap.get(dispositivo).establecerTemperatura(dispositivo, temperatura);
        // FIXME: Esto no parece estar del todo bien. Me quedan dudas de si este método deba devolver algo
        this.propertyChangeSupport.firePropertyChange(PROPERTY_NAME, temperatura, temperatura);
        return temperatura;
    }

    public void attach(PropertyChangeListener propertyChangeListener){
        propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
    }
}
