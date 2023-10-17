package org.pp2;

import java.time.LocalTime;

public class ComandoDeshabilitadoException extends RuntimeException {

    private final String comando;
    private final LocalTime horaInicio;
    private final LocalTime horaFin;

    public ComandoDeshabilitadoException(String comando, LocalTime horaInicio, LocalTime horaFin) {
        this.comando = comando;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    @Override
    public String getMessage() {
        return String.format("Error al ejecutar el comando %s. " +
                "Ejecución de comandos deshabilitada desde %s hasta %s.", comando, horaInicio.getHour(), horaFin.getHour());
    }

}
