import org.junit.jupiter.api.Test;
import org.pp2.DiscovererException;
import org.pp2.Dispositivo;
import org.pp2.DispositivoDiscoverer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class UserStory2Test {
    @Test
    void ca1DirectorioInexistente(){
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "directorioInexistente").toString();
        assertThrows(FileNotFoundException.class, () -> new DispositivoDiscoverer().discover(path));
    }

    @Test
    void ca2NoEsDispositivo() throws FileNotFoundException {
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "notDispositivo").toString();
        List<Dispositivo> dispositivos = new DispositivoDiscoverer().discover(path);
        assertTrue(dispositivos.isEmpty());
    }

    @Test
    void ca3DirectorioVacio() throws IOException {
        Path path = FileSystems.getDefault().getPath("src", "test", "resources", "directorioVacio");
        Files.createDirectories(path);
        List<Dispositivo> dispositivos = new DispositivoDiscoverer().discover(path.toString());
        assertTrue(dispositivos.isEmpty());

        Files.deleteIfExists(path);
    }

    @Test
    void ca4UnicoDispositivo() throws FileNotFoundException {
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "dispositivo").toString();
        List<Dispositivo> dispositivos = new DispositivoDiscoverer().discover(path);
        assertEquals(1, dispositivos.size());
        assertEquals(Set.of("d1"), dispositivos.stream().map(Dispositivo::getNombre).collect(Collectors.toSet()));
    }
    @Test
    void ca5MasDeUnDispositivo() throws FileNotFoundException {
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "multipleDispositivos").toString();
        List<Dispositivo> dispositivos = new DispositivoDiscoverer().discover(path);
        assertEquals(2, dispositivos.size());
        assertEquals(Set.of("d1", "d2"), dispositivos.stream().map(Dispositivo::getNombre).collect(Collectors.toSet()));
    }

    @Test
    void ca6ClaseInexistente(){
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "claseInexistente").toString();
        DiscovererException excepcion = assertThrows(DiscovererException.class, () ->
                new DispositivoDiscoverer().discover(path));
        assertEquals("Clase claseInexistente.class no encontrada", excepcion.getMessage());
    }
}
