package org.pp2;

import org.pp2.time.LocalTimeService;

import java.time.LocalTime;

import static java.time.LocalDateTime.now;

public class DispositivoProxy implements Dispositivo {

    private final Dispositivo dispositivo;
    private final LocalTime horarioInicio;
    private final LocalTime horarioFin;
    private final LocalTimeService localTimeService;
    private DispositivoObserver observer;

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
        if(isDeshabilitado())
            throw new ComandoDeshabilitadoException(comando, horarioInicio, horarioFin);

        if(observer!=null)
            observer.registrarComando(this.getNombre(), comando, now());

        dispositivo.ejecutar(comando);
    }

    private boolean isDeshabilitado() {
        LocalTime now = localTimeService.now();
        return now.isAfter(horarioInicio) && now.isBefore(horarioFin);
    }

    @Override
    public void registrarObserver(DispositivoObserver historico) {
        this.observer = historico;
    }

    @Override
    public void eliminarObserver() {

    }
}
