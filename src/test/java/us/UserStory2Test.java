package us;

import org.interfaces.ComandoDispositivoFactory;
import org.junit.jupiter.api.Test;
import org.pp2.ComandoDispositivoFactoryDiscoverer;

import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserStory2Test {
    @Test
    void ca1PathInexistente(){
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "directorioInexistente").toString();
        assertThrows(FileNotFoundException.class, () -> new ComandoDispositivoFactoryDiscoverer().discover(path));
    }

    @Test
    void ca2ClaseCargadaNoEsDriverClimatizadorFactory(){
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "distintoAComandoDispositivoFactory").toString();
        assertThrows(RuntimeException.class, () -> new ComandoDispositivoFactoryDiscoverer().discover(path));
    }

    @Test
    void ca3DirectorioVacio() throws FileNotFoundException {
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "directorioVacio").toString();
        Set<ComandoDispositivoFactory> climatizadorFactorySet = new ComandoDispositivoFactoryDiscoverer().discover(path);
        assertTrue(climatizadorFactorySet.isEmpty());
    }

    @Test
    void ca4UnicoDriverClimatizadorFactory() throws FileNotFoundException {
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "comandoDispositivoFactory").toString();
        assertEquals(1, new ComandoDispositivoFactoryDiscoverer().discover(path).size());
    }
    @Test
    void ca5MasDeUnDriverClimatizadorFactory() throws FileNotFoundException {
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "multiplesComandoDispositivoFactories").toString();
        assertTrue(new ComandoDispositivoFactoryDiscoverer().discover(path).size() > 1);
    }
}
