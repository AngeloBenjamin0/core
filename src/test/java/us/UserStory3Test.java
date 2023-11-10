package us;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pp2.ClimaTotal;

import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserStory3Test {

    ClimaTotal climaTotal;

    @BeforeEach
    public void setUp(){}


    @Test
    public void ca1EspecificacionValida() throws FileNotFoundException {
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "especificaciones", "especificacion.json").toString();
        climaTotal = new ClimaTotal(path);

        climaTotal.ejecutarComando("d1", "ENCENDER");
        assertEquals(List.of("Se ejecuta comando ENCENDER"), RegistroResultadoEjecucion.getResultadoEjecucion());
    }

    @Test
    public void ca2EspecificacionVacia() throws FileNotFoundException {
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "especificaciones", "especificacionVacia.json").toString();
        climaTotal = new ClimaTotal(path);

        IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () ->
                climaTotal.ejecutarComando("d2", "ENCENDER")
        );
        assertEquals(IllegalArgumentException.class, excepcion.getClass());
        assertEquals("Dispositivo inexistente", excepcion.getMessage());
    }

    @Test
    public void ca3DispositivoVacio() throws FileNotFoundException {
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "especificaciones", "sinDispositivo.json").toString();
        climaTotal = new ClimaTotal(path);

        IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () ->
                climaTotal.ejecutarComando("d1", "ENCENDER")
        );
        assertEquals(IllegalArgumentException.class, excepcion.getClass());
        assertEquals("Comando inexistente", excepcion.getMessage());
    }

    @Test
    public void ca4MalFormato(){
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "especificaciones", "malFormato.json").toString();

        RuntimeException excepcion = assertThrows(RuntimeException.class, () ->
                new ClimaTotal(path)
        );
        assertEquals(RuntimeException.class, excepcion.getClass());
        assertEquals("Formato de especificación inválido", excepcion.getMessage());
    }

    @Test
    public void ca5EspecificacionInexistente(){
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "especificaciones", "inexistente.json").toString();

        FileNotFoundException excepcion = assertThrows(FileNotFoundException.class, () ->
                new ClimaTotal(path)
        );
        assertEquals(FileNotFoundException.class, excepcion.getClass());

    }

}
