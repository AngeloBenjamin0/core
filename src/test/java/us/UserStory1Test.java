package us;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.pp2.Dispositivo;
import org.pp2.EstablecedorTemperatura;
import org.pp2.IntegracionClimatizadores;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;


public class UserStory1Test {
    EstablecedorTemperatura establecedorTemperatura;

    @Test
    void testHappyPath(){
        // GIVEN
        Dispositivo dispositivo = new Dispositivo("d1", "Dispositivo 1", "Samsung");
        IntegracionClimatizadores mockIntegracion = Mockito.mock(IntegracionClimatizadores.class);
        establecedorTemperatura = new EstablecedorTemperatura(Map.of(dispositivo, mockIntegracion));

        // WHEN
        int temperaturaEstablecida = establecedorTemperatura.establecer("d1", 18);

        assertEquals(18, temperaturaEstablecida);
        verify(mockIntegracion).establecerTemperatura(dispositivo, 18);
    }

    @Test
    @Disabled("Se deshabilita hasta tanto y en cuanto se defina el comportamiento de excepción de temperatura no permitida")
    void testTemperaturaNoPermitida(){
        Dispositivo dispositivo = new Dispositivo("d2", "Dispositivo 2", "Google Nest");
        IntegracionClimatizadores mockIntegracion = Mockito.mock(IntegracionClimatizadores.class);
        establecedorTemperatura = new EstablecedorTemperatura(Map.of(dispositivo, mockIntegracion));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                establecedorTemperatura.establecer("d2", 34));

        assertEquals("Temperatura 34 fuera de rango. Establecer temperatura entre 18 y 30 grados.",
                exception.getMessage());
    }

}
