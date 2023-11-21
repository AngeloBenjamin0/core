import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pp2.ClimaTotal;
import org.pp2.Dispositivo;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserStory1Test {
	private ClimaTotal climaTotal;

	@BeforeEach
	void setUp(){
		Set<Dispositivo> dispositivos = Set.of(new SamsungAdapter());

		climaTotal = new ClimaTotal(dispositivos);
	}

    @Test
    void ca1EjecucionComandoVacío() {
		assertTrue(RegistroEjecucionComando.getEjecucionComandos().isEmpty());
    }

	@Test
	void ca2EncenderDispositivo() {
		climaTotal.ejecutarComando("d", "ENCENDER");
		assertEquals(List.of("ENCENDER"), RegistroEjecucionComando.getEjecucionComandos());
	}

	@Test
	void ca3MultiplesComandos() {
		climaTotal.ejecutarComando("d", "ENCENDER");
		climaTotal.ejecutarComando("d", "ENCENDER");
		assertEquals(List.of("ENCENDER", "ENCENDER"), RegistroEjecucionComando.getEjecucionComandos());
	}


	@Test
    void ca4ComandoInexistente() {
    	IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () ->
				climaTotal.ejecutarComando("d", "comandoInexistente")
    	);
    	assertEquals(IllegalArgumentException.class, excepcion.getClass());
    	assertEquals("Comando inexistente", excepcion.getMessage());
    }

	@Test
	void ca5DispositivoInexistente(){
		IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () ->
				climaTotal.ejecutarComando("noExiste", "ENCENDER")
		);
		assertEquals(IllegalArgumentException.class, excepcion.getClass());
		assertEquals("Dispositivo inexistente", excepcion.getMessage());
	}


	@AfterEach
	void tearDown(){
		RegistroEjecucionComando.clearEjecucionComandos();
	}

}