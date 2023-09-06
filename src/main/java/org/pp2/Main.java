package org.pp2;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String dispositivoConfigFile = args[0];
        String dispositivoId = args[1];
        int temperatura = Integer.parseInt(args[2]);
        ClimaTotal.init(dispositivoConfigFile).establecer(dispositivoId, temperatura);
    }
}