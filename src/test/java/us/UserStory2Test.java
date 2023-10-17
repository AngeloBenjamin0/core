package us;

import org.junit.jupiter.api.Test;
import org.pp2.Dispositivo;
import org.pp2.DispositivoDiscoverer;

import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserStory2Test {
    @Test
    void ca1PathInexistente(){
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "directorioInexistente").toString();
        assertThrows(FileNotFoundException.class, () -> new DispositivoDiscoverer().discover(path));
    }

    @Test
    void ca2ClaseCargadaNoEsDispositivo(){
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "notDispositivo").toString();
        assertThrows(RuntimeException.class, () -> new DispositivoDiscoverer().discover(path));
    }

    @Test
    void ca3DirectorioVacio() throws FileNotFoundException {
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "directorioVacio").toString();
        List<Dispositivo> dispositivos = new DispositivoDiscoverer().discover(path);
        assertTrue(dispositivos.isEmpty());
    }

    @Test
    void ca4UnicoDispositivo() throws FileNotFoundException {
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "dispositivo").toString();
        assertEquals(1, new DispositivoDiscoverer().discover(path).size());
    }
    @Test
    void ca5MasDeUnDispositivo() throws FileNotFoundException {
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "multipleDispositivos").toString();
        assertTrue(new DispositivoDiscoverer().discover(path).size() > 1);
    }
}