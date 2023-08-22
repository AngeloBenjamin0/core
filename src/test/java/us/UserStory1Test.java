package us;

import org.junit.jupiter.api.Test;
import org.pp2.Controlador;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserStory1Test {

    Controlador controlador = new Controlador();

    @Test
    void testHappyPath(){
        String nombreDispositivo = "Dispositivo 1";
        int temperatura = 18;

        int temperaturaEstablecida = controlador.establecerTemperatura(nombreDispositivo, temperatura);


        assertEquals(temperatura, temperaturaEstablecida);
    }

}
