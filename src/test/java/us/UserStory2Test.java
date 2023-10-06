package us;

import org.junit.jupiter.api.Test;
import org.pp2.DriverFactory;
import org.pp2.DriverFactoryDiscoverer;

import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserStory2Test {
    @Test
    void ca1PathInexistente(){
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "directorioInexistente").toString();
        assertThrows(FileNotFoundException.class, () -> new DriverFactoryDiscoverer().discover(path));
    }

    @Test
    void ca2ClaseCargadaNoEsDriverClimatizadorFactory(){
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "distintoAComandoDispositivoFactory").toString();
        assertThrows(RuntimeException.class, () -> new DriverFactoryDiscoverer().discover(path));
    }

    @Test
    void ca3DirectorioVacio() throws FileNotFoundException {
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "directorioVacio").toString();
        Set<DriverFactory> climatizadorFactorySet = new DriverFactoryDiscoverer().discover(path);
        assertTrue(climatizadorFactorySet.isEmpty());
    }

    @Test
    void ca4UnicoDriverClimatizadorFactory() throws FileNotFoundException {
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "comandoDispositivoFactory").toString();
        assertEquals(1, new DriverFactoryDiscoverer().discover(path).size());
    }
    @Test
    void ca5MasDeUnDriverClimatizadorFactory() throws FileNotFoundException {
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "multiplesComandoDispositivoFactories").toString();
        assertTrue(new DriverFactoryDiscoverer().discover(path).size() > 1);
    }
}
