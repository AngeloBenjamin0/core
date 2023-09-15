package us;

import org.junit.jupiter.api.Test;
import org.pp2.DriverClimatizadorFactory;
import org.pp2.DriverClimatizadorFactoryDiscoverer;

import java.nio.file.FileSystems;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
/*
US Extensiblidad: Quiero agregar un climatizador
 */
public class UserStory2Test {
    final static int TAMANO_VACIO = 0;
    final static String PATH_INEXISTENTE = FileSystems.getDefault().getPath("src", "test", "resources", "resources_").toString();
    final static String PATH_VACIO = FileSystems.getDefault().getPath("src", "test", "resources", "resources_vacio").toString();
    final static String PATH_CLIMATIZADOR_INVALIDO = FileSystems.getDefault().getPath("src", "test", "resources").toString();
    @Test
    void testPathInexistente(){
        Set<DriverClimatizadorFactory> climatizadorFactorySet = new DriverClimatizadorFactoryDiscoverer().discover(PATH_INEXISTENTE);
        assertEquals(TAMANO_VACIO, climatizadorFactorySet.size());
    }

    @Test
    void testDriverClimatizadorInvalido(){ //throws RunTimeException
        Set<DriverClimatizadorFactory> climatizadorFactorySet = new DriverClimatizadorFactoryDiscoverer().discover(PATH_CLIMATIZADOR_INVALIDO);
        assertEquals(TAMANO_VACIO, climatizadorFactorySet.size());
    }

    @Test
    void testDirectorioVacio(){
        Set<DriverClimatizadorFactory> climatizadorFactorySet = new DriverClimatizadorFactoryDiscoverer().discover(PATH_VACIO);
        assertTrue(climatizadorFactorySet.isEmpty());
    }

    @Test
    void testUnicoDriverClimatizador(){
        //assertEquals(1, climatizadorFactorySet.size())
    }
    @Test
    void testMultiplesDriverClimatizadores(){
        //assertTrue(limatizadorFactorySet.size() > 1)
    }
}
