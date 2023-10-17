package us;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pp2.ClimaTotal;
import org.pp2.Dispositivo;
import org.pp2.registro_comandos.CreadorCSV;
import org.pp2.registro_comandos.Historico;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserStory4Test {
    private Dispositivo dispositivo;
    Historico historico;
    @BeforeEach
    void setUp() throws FileNotFoundException {
        String dispositivosPath = FileSystems.getDefault().getPath("src", "test", "resources", "dispositivo").toString();
        String propertiesPath = FileSystems.getDefault().getPath("src", "test", "resources", "application.properties").toString();
        ClimaTotal climaTotal = new ClimaTotal(dispositivosPath, propertiesPath);
        List<Dispositivo> dispositivos = climaTotal.getDispositivos();
        dispositivo = dispositivos.get(0);
        historico = new Historico();
    }
    @Test
    void ca1ReporteGenerado() throws IOException {
        dispositivo.registrarObserver(historico);
        dispositivo.ejecutar("ENCENDER");
        String filePath = "src/historico1";
        new CreadorCSV(historico).crearCSV(filePath);

        File file = new File(filePath);
        List<String> fileLines = Files.readAllLines(Path.of(file.getPath()));

        assertTrue(file.exists());
        assertEquals(fileLines.size(), 2);
        assertEquals(fileLines.get(0), "DispositivoNombre,Comando,Timestamp");
        assertEquals(fileLines.get(1), "Samsung v2,ENCENDER,2020-01-01T10:00");
    }

    @Test
    void ca2ReporteVacio() throws IOException {
        dispositivo.registrarObserver(historico);
        String filePath = "src/historico1";
        new CreadorCSV(historico).crearCSV(filePath);

        File file = new File(filePath);
        List<String> fileLines = Files.readAllLines(Path.of(file.getPath()));

        assertTrue(file.exists());
        assertEquals(fileLines.size(), 1);
        assertEquals(fileLines.get(0), "DispositivoNombre,Comando,Timestamp");
    }

}
