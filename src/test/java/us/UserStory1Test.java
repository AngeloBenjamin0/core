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
		String dispositivoJsonPath = FileSystems.getDefault().getPath("src", "test", "resources", "dispositivos.json").toString();
		String driverClimatizadorFactoriesPath = FileSystems.getDefault().getPath("src", "test", "resources").toString();
		List<Dispositivo> dispositivos = ClimaTotal.inicializarDispositivos(dispositivoJsonPath, driverClimatizadorFactoriesPath);
		dispositivo = dispositivos.get(0);
	}
    
    @Test
    void encenderClimatizador() {
    	dispositivo.ejecutar("ENCENDER");
    }
    
    @Test
    void enviarComandoNoSoportado() {
    	IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () ->
    		dispositivo.ejecutar("ESTABLECER TEMPERATURA")
    	);
    	assertEquals(IllegalArgumentException.class, excepcion.getClass());
    	assertEquals("Comando no soportado", excepcion.getMessage());
    }
    
    @Test
    void comandoNoSoportadoPorClimatizador() {
		RuntimeException excepcion = assertThrows(RuntimeException.class, () ->
    		dispositivo.ejecutar("ENFRIAR")
    	);
    	assertEquals(RuntimeException.class, excepcion.getClass());
    	assertEquals("Operación no aceptada por Samsung-V6", excepcion.getMessage());
    }

}