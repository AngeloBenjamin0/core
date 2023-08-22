package us;

import org.junit.jupiter.api.Test;
import org.pp2.Controlador;
import org.pp2.Dispositivo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserStory1Test {

    Controlador controlador = new Controlador();

    @Test
    void testHappyPath(){
        Dispositivo dispositivo = new Dispositivo("Dispositivo 1");
        int temperatura = 18;

        int temperaturaEstablecida = controlador.establecerTemperatura(dispositivo, temperatura);

        assertEquals(temperatura, temperaturaEstablecida);
    }

}
