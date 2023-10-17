package org.pp2.registro_comandos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CreadorCSV {
    private Historico historico;

    public CreadorCSV(Historico historico) {
        this.historico = historico;
    }

    public void crearCSV(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            writer.write("DispositivoNombre,Comando,Timestamp\n");


            for (ComandoRegistro registro : historico.getRegistros()) {
                writer.write(String.format("%s,%s,%s\n",
                        registro.getDispositivoNombre(), registro.getComando(), registro.getTimestamp()));
            }

            System.out.println("CSV creado en: " + filePath);
        } catch (IOException e) {
            System.err.println("Error creando el CSV: " + e.getMessage());
        }
    }
}