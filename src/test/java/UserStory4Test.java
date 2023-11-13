import org.junit.jupiter.api.Test;
import org.pp2.ClimaTotal;
import org.pp2.ClimaTotalFactory;

import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserStory4Test {

    @Test
    public void ca1DispositivoCompuesto() throws FileNotFoundException {
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "especificaciones", "especificacionCompuesta.json").toString();
        ClimaTotal climaTotal = new ClimaTotalFactory(path).crear();

        assertTrue(RegistroResultadoEjecucion.getResultadoEjecucion().isEmpty());
        climaTotal.ejecutarComando("d1", "ENCENDER");

        assertEquals(List.of("INFO - Se ejecuta comando ENCENDER en dispositivo d1", "Se ejecuta comando ENCENDER"), RegistroResultadoEjecucion.getResultadoEjecucion());
    }

    @Test
    public void ca2NoEsDispositivo(){
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "especificaciones", "unoNoEsDispositivo.json").toString();

        RuntimeException excepcion = assertThrows(RuntimeException.class, () ->
                new ClimaTotalFactory(path).crear()
        );
        assertEquals(RuntimeException.class, excepcion.getClass());
        assertEquals("La clase NotADispositivo no es un Dispositivo", excepcion.getMessage());
    }
}
