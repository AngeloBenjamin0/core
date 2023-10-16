package org.pp2;

import java.io.FileNotFoundException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String propertiesPath = args[0];
        String dispositivosPath = args[1];
        String nombreDispositivo = args[2];
        String comando = args[3];

        ClimaTotal climaTotal = new ClimaTotal(dispositivosPath, propertiesPath);
        Optional<Dispositivo> dispositivo = climaTotal.getDispositivos().stream().filter(d -> d.getNombre().equals(nombreDispositivo)).findAny();

        if (dispositivo.isEmpty()) throw new RuntimeException(String.format("No se encuentra el dispositivo %s", nombreDispositivo));
        dispositivo.get().ejecutar(comando);
    }
}