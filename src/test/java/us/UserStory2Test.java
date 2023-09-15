package us;

import org.junit.jupiter.api.Test;
import org.pp2.DriverClimatizadorFactory;
import org.pp2.DriverClimatizadorFactoryDiscoverer;

import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserStory2Test {
    @Test
    void testPathInexistente(){
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "resources_").toString();
        assertThrows(FileNotFoundException.class, () -> new DriverClimatizadorFactoryDiscoverer().discover(path));
    }

    @Test
    void testClaseCargadaNoEsDriverClimatizadorFactory(){
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "clasesDistintasADriverClimatizadorFactory").toString();
        assertThrows(RuntimeException.class, () -> new DriverClimatizadorFactoryDiscoverer().discover(path));
    }

    @Test
    void testDirectorioVacio() throws FileNotFoundException {
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "vacio").toString();
        Set<DriverClimatizadorFactory> climatizadorFactorySet = new DriverClimatizadorFactoryDiscoverer().discover(path);
        assertTrue(climatizadorFactorySet.isEmpty());
    }

    @Test
    void testUnicoDriverClimatizadorFactory() throws FileNotFoundException {
        String path = FileSystems.getDefault().getPath("src", "test", "resources").toString();
        assertEquals(1, new DriverClimatizadorFactoryDiscoverer().discover(path).size());
    }
    @Test
    void testMasDeUnDriverClimatizadorFactory() throws FileNotFoundException {
        String path = FileSystems.getDefault().getPath("src", "test", "resources", "masDeUnDriverClimatizadorFactory").toString();
        assertTrue(new DriverClimatizadorFactoryDiscoverer().discover(path).size() > 1);
    }
}
