package us;

import org.junit.jupiter.api.Test;
import org.pp2.DriverClimatizadorFactory;
import org.pp2.DriverClimatizadorFactoryDiscoverer;

import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/*
US Extensiblidad: Quiero agregar un climatizador
 */
public class UserStory2Test {
    final static String PATH_INEXISTENTE = FileSystems.getDefault().getPath("src", "test", "resources", "resources_").toString();
    final static String PATH_SIN_DISCOVERER_FACTORIES = FileSystems.getDefault().getPath("src", "test", "resources", "vacio").toString();
    final static String PATH_CLASES_NO_DRIVER_CLIMATIZADOR_FACTORY = FileSystems.getDefault().getPath("src", "test", "resources", "clasesDistintasADriverClimatizadorFactory").toString();
    @Test
    void testPathInexistente(){
        assertThrows(FileNotFoundException.class, () -> new DriverClimatizadorFactoryDiscoverer().discover(PATH_INEXISTENTE));
    }

    @Test
    void testClaseCargadaNoEsDriverClimatizadorFactory(){
        assertThrows(RuntimeException.class, () -> new DriverClimatizadorFactoryDiscoverer().discover(PATH_CLASES_NO_DRIVER_CLIMATIZADOR_FACTORY));
    }

    @Test
    void testDirectorioVacio() throws FileNotFoundException {
        Set<DriverClimatizadorFactory> climatizadorFactorySet = new DriverClimatizadorFactoryDiscoverer().discover(PATH_SIN_DISCOVERER_FACTORIES);
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
