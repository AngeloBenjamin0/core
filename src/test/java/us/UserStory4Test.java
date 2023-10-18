package us;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pp2.ClimaTotal;
import org.pp2.Dispositivo;
import org.pp2.registro_comandos.CreadorCSV;
import org.pp2.registro_comandos.Historico;
import org.pp2.time.LocalTimeService;
import org.pp2.time.TestLocalTimeService;

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
    LocalTimeService localTimeService;
    final String HEADER_ESPERADO = "DispositivoNombre,Comando,Timestamp";
    @BeforeEach
    void setUp() throws FileNotFoundException {
        String dispositivosPath = FileSystems.getDefault().getPath("src", "test", "resources", "dispositivo").toString();
        String propertiesPath = FileSystems.getDefault().getPath("src", "test", "resources", "application.properties").toString();
        ClimaTotal climaTotal = new ClimaTotal(dispositivosPath, propertiesPath);
        List<Dispositivo> dispositivos = climaTotal.getDispositivos();
        dispositivo = dispositivos.get(0);
        historico = new Historico();
        localTimeService = new TestLocalTimeService();
    }
    @Test
    void ca1ReporteGenerado() throws IOException {
        final String PRIMER_REGISTRO_ESPERADO = "Samsung v2,ENCENDER,2020-01-01T10:00";
        final String FILE_PATH = FileSystems.getDefault().getPath("src", localTimeService.now().toString().replace(":","_") + ".csv").toString();
        System.out.println(FILE_PATH);
        dispositivo.registrarObserver(historico);
        dispositivo.ejecutar("ENCENDER");

        new CreadorCSV(historico, localTimeService).crearCSV();

        File file = new File(FILE_PATH);
        List<String> fileLines = Files.readAllLines(Path.of(file.getPath()));

        assertTrue(file.exists());
        assertEquals(2, fileLines.size());
        assertEquals(HEADER_ESPERADO, fileLines.get(0));
        assertEquals(PRIMER_REGISTRO_ESPERADO, fileLines.get(1));
    }

    @Test
    void ca2ReporteVacio() throws IOException {
        final String FILE_PATH = FileSystems.getDefault().getPath("src", localTimeService.now().toString().replace(":","_") + ".csv").toString();

        dispositivo.registrarObserver(historico);
        new CreadorCSV(historico, new TestLocalTimeService()).crearCSV();
        File file = new File(FILE_PATH);
        List<String> fileLines = Files.readAllLines(Path.of(file.getPath()));

        assertTrue(file.exists());
        assertEquals(1, fileLines.size());
        assertEquals(HEADER_ESPERADO, fileLines.get(0));
    }

}
