package org.pp2;

import java.io.FileNotFoundException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String dispositivosPath = args[0];
        String nombreDispositivo = args[1];
        String comando = args[2];

        ClimaTotal climaTotal = new ClimaTotal(dispositivosPath);
        Optional<Dispositivo> dispositivo = climaTotal.getDispositivos().stream().filter(d -> d.getNombre().equals(nombreDispositivo)).findAny();

        if (dispositivo.isEmpty()) throw new RuntimeException(String.format("No se encuentra el dispositivo %s", nombreDispositivo));
        dispositivo.get().ejecutar(comando);
    }
}