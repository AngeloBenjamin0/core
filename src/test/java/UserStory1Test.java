import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pp2.ClimaTotal;
import org.pp2.Dispositivo;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserStory1Test {
	private ClimaTotal climaTotal;

	@BeforeEach
	void setUp(){
		Set<Dispositivo> dispositivos = Set.of(new SamsungAdapter());

		climaTotal = new ClimaTotal(dispositivos);
	}

    @Test
    void ca1EncenderDispositivo() {
		climaTotal.ejecutarComando("d1", "ENCENDER");
		assertEquals(List.of("Se ejecuta comando ENCENDER"), RegistroEjecucionComando.getEjecucionComandos());
    }

    @Test
    void ca2ComandoInexistente() {
    	IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () ->
				climaTotal.ejecutarComando("d1", "comandoInexistente")
    	);
    	assertEquals(IllegalArgumentException.class, excepcion.getClass());
    	assertEquals("Comando inexistente", excepcion.getMessage());
    }

	@Test
	void ca3DispositivoInexistente(){
		IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () ->
				climaTotal.ejecutarComando("d2", "ENCENDER")
		);
		assertEquals(IllegalArgumentException.class, excepcion.getClass());
		assertEquals("Dispositivo inexistente", excepcion.getMessage());
	}

	@AfterEach
	void tearDown(){
		RegistroEjecucionComando.clearEjecucionComandos();
	}

}