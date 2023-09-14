package org.pp2;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args){
        String dispositivoJsonPath = args[0];
        String dispositivoId = args[1];
        String comando = args[2];
        List<Dispositivo> dispositivos = ClimaTotal.inicializarDispositivos(dispositivoJsonPath);

        Optional<Dispositivo> dispositivo = dispositivos.stream().filter(d -> d.getId().equals(dispositivoId)).findAny();

        if (dispositivo.isEmpty()) throw new RuntimeException(String.format("No se encuentra el dispositivo %s", dispositivoId));
        dispositivo.get().ejecutar(comando);

    }
}