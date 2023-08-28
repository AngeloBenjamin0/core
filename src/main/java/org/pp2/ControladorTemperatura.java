package org.pp2;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ControladorTemperatura {

    private final PropertyChangeSupport propertyChangeSupport;

    public ControladorTemperatura() {
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public int establecer(Dispositivo dispositivo, int temperatura){
        // opción 1 - No pasa nada ya que devuelve void. En caso de error, exception
        // opción 2 - Devuelve un booleano - True: Establecido / False: Fallo
        // opción 3 - Devuelvo un entero con la temperatura establecida. En caso de error, exception <- CHOSE ONE
        if (temperatura > 30 || temperatura < 18)
            throw new IllegalArgumentException(String.format("Temperatura %s fuera de rango. Establecer temperatura entre 18 y 30 grados.", temperatura));
        return 18;
    }

    public void attach(PropertyChangeListener propertyChangeListener){
        propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
    }

    public int establecer(String id, int temperatura){
        // TODO: Este método será eliminado más avanzado el proyecto.
        // La existencia del mismo es sólo para tener un método que sea utilizado desde la vista y el controlador
        propertyChangeSupport.firePropertyChange("temperatura", temperatura, temperatura + 1); // FIXME: Cambiar estos valores. Son totalmente arbitrarios. No siguen ninguna regla.
        return this.establecer(new Dispositivo(id), temperatura);
    }

}
