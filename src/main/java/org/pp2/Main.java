package org.pp2;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String proxyFactoriesPath = args[0];
        String dispositivosPath = args[1];
        String nombreDispositivo = args[2];
        String comando = args[3];

        ClimaTotal climaTotal = new ClimaTotal(dispositivosPath, proxyFactoriesPath);
        climaTotal.ejecutarComando(nombreDispositivo, comando);
    }
}