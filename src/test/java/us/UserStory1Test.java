package us;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pp2.ClimaTotal;
import org.pp2.Dispositivo;

import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserStory1Test {
	private Dispositivo dispositivo;
	
	@BeforeEach
	void setUp() throws FileNotFoundException {
		String dispositivosPath = FileSystems.getDefault().getPath("src", "test", "resources", "dispositivo").toString();
		String propertiesPath = FileSystems.getDefault().getPath("src", "test", "resources", "application.properties").toString();
		ClimaTotal climaTotal = new ClimaTotal(dispositivosPath, propertiesPath);
		List<Dispositivo> dispositivos = climaTotal.getDispositivos();
		dispositivo = dispositivos.get(0);
	}

    @Test
    void ca1EncenderClimatizador() {
    	dispositivo.ejecutar("ENCENDER");
    }

    @Test
    void ca2EnviarComandoNoSoportado() {
    	IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () ->
    		dispositivo.ejecutar("ESTABLECER TEMPERATURA")
    	);
    	assertEquals(IllegalArgumentException.class, excepcion.getClass());
    	assertEquals("Comando no soportado", excepcion.getMessage());
    }

}