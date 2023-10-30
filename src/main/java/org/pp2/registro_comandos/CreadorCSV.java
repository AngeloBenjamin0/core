package org.pp2.registro_comandos;

import org.pp2.time.LocalTimeService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CreadorCSV {
    private Historico historico;
    private final LocalTimeService localTimeService;
    public CreadorCSV(Historico historico, LocalTimeService localTimeService) {
        this.historico = historico;
        this.localTimeService = localTimeService;
    }

    public void crearCSV() {
        String filePath = FileSystems.getDefault().getPath("src", localTimeService.now().toString().replace(":","_") + ".csv").toString();
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