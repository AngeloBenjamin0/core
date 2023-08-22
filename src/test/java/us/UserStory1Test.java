package us;

import org.junit.jupiter.api.Test;
import org.pp2.Controlador;
import org.pp2.Dispositivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserStory1Test {

    Controlador controlador = new Controlador();

    @Test
    void testHappyPath(){
        Dispositivo dispositivo = new Dispositivo("Dispositivo 1");
        int temperatura = 18;

        int temperaturaEstablecida = controlador.establecerTemperatura(dispositivo, temperatura);

        assertEquals(temperatura, temperaturaEstablecida);
    }

    @Test
    void testTemperaturaNoPermitida(){
        Dispositivo dispositivo = new Dispositivo("Dispositivo 2");
        int temperatura = 34;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controlador.establecerTemperatura(dispositivo, temperatura));

        assertEquals(String.format("Temperatura %s fuera de rango. Establecer temperatura entre 18 y 30 grados.", temperatura),
                exception.getMessage());
    }

}
