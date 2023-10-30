package us;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pp2.ClimaTotal;

import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserStory1Test {
	private ClimaTotal climaTotal;

	@BeforeEach
	void setUp() throws FileNotFoundException {
		String dispositivosPath = FileSystems.getDefault().getPath("src", "test", "resources", "dispositivo").toString();
		String propertiesPath = FileSystems.getDefault().getPath("src", "test", "resources", "application.properties").toString();

		climaTotal = new ClimaTotal(dispositivosPath, propertiesPath);
	}

    @Test
    void ca1EncenderDispositivo() {
		climaTotal.ejecutarComando("d1", "ENCENDER");
		assertEquals(List.of("Se ejecuta comando ENCENDER"), RegistroResultadoEjecucion.getResultadoEjecucion());
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
		RegistroResultadoEjecucion.clearResultadoEjecucion();
	}

}