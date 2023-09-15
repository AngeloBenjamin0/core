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
    final static String PATH_VACIO = System.getProperty("user.dir") + "\\src\\test\\resources\\resources_vacio";
    final static String PATH_SIN_CLIMATIZADOR = System.getProperty("user.dir") + "\\src\\test\\resources";
    @Test
    void testDriverClimatizadorInexistente(){
        Set<DriverClimatizadorFactory> climatizadorFactorySet = new DriverClimatizadorFactoryDiscoverer().discover(PATH_VACIO);
        assertEquals(EMPTY_SIZE, climatizadorFactorySet.size());
    }

    @Test
    void testDriverClimatizadorInvalido(){
        Set<DriverClimatizadorFactory> climatizadorFactorySet = new DriverClimatizadorFactoryDiscoverer().discover(PATH_SIN_CLIMATIZADOR);
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
