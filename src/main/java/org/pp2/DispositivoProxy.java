package org.pp2;

import org.pp2.time.LocalTimeService;

import java.time.LocalTime;

public class DispositivoProxy implements Dispositivo {

    private final Dispositivo dispositivo;
    private final LocalTime horarioInicio;
    private final LocalTime horarioFin;
    private final LocalTimeService localTimeService;

    public DispositivoProxy(Dispositivo dispositivo, int horarioInicio, int horarioFin, LocalTimeService localTimeService) {
        this.dispositivo = dispositivo;
        this.horarioInicio = LocalTime.of(horarioInicio, 0, 0);
        this.horarioFin = LocalTime.of(horarioFin, 0 , 0);
        this.localTimeService = localTimeService;
    }

    @Override
    public String getNombre() {
        return dispositivo.getNombre();
    }

    @Override
    public void ejecutar(String comando) {
        LocalTime now = localTimeService.now();
        if (now.isAfter(horarioInicio) && now.isBefore(horarioFin))
            throw new RuntimeException("La ejecución de comandos no está habilitada");

        dispositivo.ejecutar(comando);
    }
}
