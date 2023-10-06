package org.pp2;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String dispositivoJsonPath = args[0];
        String driverClimatizadorFactoriesPath = args[1];
        String dispositivoId = args[2];
        String comando = args[3];
        List<DispositivoAdapter> dispositivos = ClimaTotal.inicializarDispositivos(dispositivoJsonPath, driverClimatizadorFactoriesPath);

        Optional<DispositivoAdapter> dispositivo = dispositivos.stream().filter(d -> d.getId().equals(dispositivoId)).findAny();

        if (dispositivo.isEmpty()) throw new RuntimeException(String.format("No se encuentra el dispositivo %s", dispositivoId));
        dispositivo.get().ejecutar(comando);
    }
}