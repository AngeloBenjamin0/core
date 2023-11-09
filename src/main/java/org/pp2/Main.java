package org.pp2;

public class Main {
    public static void main(String[] args){
        String especificacion = args[0];
        String nombreDispositivo = args[1];
        String comando = args[2];

        ClimaTotal climaTotal = new ClimaTotal(especificacion);
        climaTotal.ejecutarComando(nombreDispositivo, comando);
    }
}