package org.pp2;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String especificacion = args[0];
        String nombreDispositivo = args[1];
        String comando = args[2];

        ClimaTotal climaTotal = new ClimaTotalFactory(especificacion).crear();
        climaTotal.ejecutarComando(nombreDispositivo, comando);
    }
}