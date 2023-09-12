package org.pp2.comando;

import org.pp2.Dispositivo;
import org.pp2.DriverClimatizador;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class InterpreteDriverClimatizador implements Interprete {

    private final DriverClimatizador driverClimatizador;
    private final Set<Comando> comandosSoportados;
    private final Map<String, Function<Dispositivo, Void>> ejecutoresComando;

    public InterpreteDriverClimatizador(DriverClimatizador driverClimatizador, Set<Comando> comandosSoportados) {
        this.driverClimatizador = driverClimatizador;
        this.comandosSoportados = comandosSoportados;
        Map<String, Function<Dispositivo, Void>> ejecutoresComando = new HashMap<>();
        ejecutoresComando.put("ENCENDER", driverClimatizador::encender);
    }

    @Override
    public void ejecutar(Dispositivo dispositivo, Comando comando) {
        if (!comandosSoportados.contains(comando)) throw new IllegalArgumentException("Comando no aceptado");

        ejecutoresComando.get("ENCENDER").apply(dispositivo);
        switch (comando.getNombreComando()){
            case "ENCENDER" : driverClimatizador.encender(dispositivo); break;
            case "APAGAR" : driverClimatizador.apagar(dispositivo); break;
            default: throw new IllegalArgumentException("El dispositivo no acepta el comando solicitado");
        }
    }
}
