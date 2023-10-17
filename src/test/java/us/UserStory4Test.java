package us;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pp2.ClimaTotal;
import org.pp2.Dispositivo;
import org.pp2.registro_comandos.CreadorCSV;
import org.pp2.registro_comandos.Historico;

import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.util.List;

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
    void ca1(){
        dispositivo.registrarObserver(historico);
        dispositivo.ejecutar("ENCENDER");
        new CreadorCSV(historico).crearCSV("src/historico1");
    }

}
