package us;

import org.junit.jupiter.api.Test;
import org.pp2.ControladorTemperatura;
import org.pp2.Dispositivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserStory1Test {

    ControladorTemperatura controlador = new ControladorTemperatura();

    @Test
    void testHappyPath(){
        Dispositivo dispositivo = new Dispositivo("Dispositivo 1");
        int temperatura = 18;

        int temperaturaEstablecida = controlador.establecer(dispositivo, temperatura);

        assertEquals(temperatura, temperaturaEstablecida);
    }

    @Test
    void testTemperaturaNoPermitida(){
        Dispositivo dispositivo = new Dispositivo("Dispositivo 2");
        int temperatura = 34;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controlador.establecer(dispositivo, temperatura));

        assertEquals(String.format("Temperatura %s fuera de rango. Establecer temperatura entre 18 y 30 grados.", temperatura),
                exception.getMessage());
    }

}
