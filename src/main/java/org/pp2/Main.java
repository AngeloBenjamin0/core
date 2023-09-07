package org.pp2;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String dispositivoJsonConfigPath = args[0];
        String dispositivoId = args[1];
        int temperatura = Integer.parseInt(args[2]);
        ClimaTotal.init(dispositivoJsonConfigPath).establecer(dispositivoId, temperatura);
    }
}