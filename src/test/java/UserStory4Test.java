import org.junit.jupiter.api.Test;
import org.pp2.ClimaTotal;
import org.pp2.ClimaTotalFactory;

import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserStory4Test {

    @Test
    public void ca1EspecificacionCompuesta() throws FileNotFoundException {
        String path = FileSystems.getDefault().getPath("especificaciones", "especificacionCompuesta.json").toString();
        ClimaTotal climaTotal = new ClimaTotalFactory(path).crear();

        assertTrue(RegistroEjecucionComando.getEjecucionComandos().isEmpty());
        climaTotal.ejecutarComando("d", "ENCENDER");

        assertEquals(List.of("INFO - ENCENDER", "ENCENDER"), RegistroEjecucionComando.getEjecucionComandos());
    }

    @Test
    public void ca2NoEsDispositivo(){
        String path = FileSystems.getDefault().getPath("especificaciones", "unoNoEsDispositivo.json").toString();

        RuntimeException excepcion = assertThrows(RuntimeException.class, () ->
                new ClimaTotalFactory(path).crear()
        );
        assertEquals(RuntimeException.class, excepcion.getClass());
        assertEquals("La clase NotADispositivo no es un Dispositivo", excepcion.getMessage());
    }

    @Test
    public void ca3DosIntegracionesConcretas(){
        String path = FileSystems.getDefault().getPath("especificaciones", "especificacionCompuestaConcreta.json").toString();

        IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () ->
                new ClimaTotalFactory(path).crear()
        );
        assertEquals(IllegalArgumentException.class, excepcion.getClass());
        assertEquals("SamsungAdapter no tiene un constructor con un parámetro de tipo Dispositivo.", excepcion.getMessage());
    }

    @Test
    public void ca4DosIntegracionesConReferencias(){
        String path = FileSystems.getDefault().getPath("especificaciones", "especificacionCompuestaConReferencia.json").toString();

        IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () ->
                new ClimaTotalFactory(path).crear()
        );
        assertEquals(IllegalArgumentException.class, excepcion.getClass());
        assertEquals("DispositivoLoggerProxy no debe recibir parámetros en el constructor", excepcion.getMessage());
    }

}
