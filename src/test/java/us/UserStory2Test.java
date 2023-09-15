package us;

import org.junit.jupiter.api.Test;
import org.pp2.DriverClimatizadorFactory;
import org.pp2.DriverClimatizadorFactoryDiscoverer;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
/*
US Extensiblidad: Quiero agregar un climatizador
 */
public class UserStory2Test {
    final int EMPTY_SIZE = 0;
    @Test
    void testDriverClimatizadorInexistente(){

        final String PATH_WITHOUT_CLASS = System.getProperty("user.dir") + "\\src\\main\\resources";
        Set<DriverClimatizadorFactory> climatizadorFactorySet = new DriverClimatizadorFactoryDiscoverer().discover(PATH_WITHOUT_CLASS);
        assertEquals(EMPTY_SIZE, climatizadorFactorySet.size());
    }

    @Test
    void testDriverClimatizadorInvalido(){
        final String PATH_WITHOUT_CLIMATIZADOR = System.getProperty("user.dir") + "\\src\\test\\java\\climatizador_invalido_lib";
        Set<DriverClimatizadorFactory> climatizadorFactorySet = new DriverClimatizadorFactoryDiscoverer().discover(PATH_WITHOUT_CLIMATIZADOR);
        assertEquals(EMPTY_SIZE, climatizadorFactorySet.size());
    }

    @Test
    void testDirectorioVacio(){
    }

    @Test
    void testUnicoDriverClimatizador(){
    }
    @Test
    void testMultiplesDriverClimatizadores(){
    }
}
