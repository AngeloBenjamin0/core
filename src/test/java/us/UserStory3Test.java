package us;

import org.junit.jupiter.api.Test;
import org.pp2.ClimaTotal;
import org.pp2.ComandoDeshabilitadoException;

import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserStory3Test {

    @Test
    public void ca1HorarioHabilitado() throws FileNotFoundException {
        String propertiesPath = FileSystems.getDefault().getPath("src", "test", "resources", "application-us3-ca1.properties").toString();
        String dispositivosPath = FileSystems.getDefault().getPath("src", "test", "resources", "dispositivo").toString();
        ClimaTotal climaTotal = new ClimaTotal(dispositivosPath, propertiesPath);

        climaTotal.ejecutarComando("d1", "ENCENDER");
        assertEquals(List.of("Se ejecuta comando ENCENDER"), RegistroResultadoEjecucion.getResultadoEjecucion());
    }

    @Test
    public void ca2HorarioInhabilitado() throws FileNotFoundException {
        String propertiesPath = FileSystems.getDefault().getPath("src", "test", "resources", "application-us3-ca2.properties").toString();
        String dispositivosPath = FileSystems.getDefault().getPath("src", "test", "resources", "dispositivo").toString();
        ClimaTotal climaTotal = new ClimaTotal(dispositivosPath, propertiesPath);

        ComandoDeshabilitadoException excepcion = assertThrows(ComandoDeshabilitadoException.class, () ->
                climaTotal.ejecutarComando("d1", "ENCENDER"));

        assertEquals(ComandoDeshabilitadoException.class, excepcion.getClass());
        assertEquals(excepcion.getMessage(), "Error al ejecutar el comando ENCENDER. Ejecución de comandos deshabilitada desde 9 hasta 12.");
    }

}
