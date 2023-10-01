package us;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pp2.ClimaTotal;
import org.pp2.DispositivoConcreto;

import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserStory1Test {
	private DispositivoConcreto dispositivoConcreto;
	
	@BeforeEach
	void setUp() throws FileNotFoundException {
		String dispositivoJsonPath = FileSystems.getDefault().getPath("src", "test", "resources", "dispositivos.json").toString();
		String driverClimatizadorFactoriesPath = FileSystems.getDefault().getPath("src", "test", "resources", "unDriverClimatizadorFactory").toString();
		List<DispositivoConcreto> dispositivoConcretos = ClimaTotal.inicializarDispositivos(dispositivoJsonPath, driverClimatizadorFactoriesPath);
		dispositivoConcreto = dispositivoConcretos.get(0);
	}
    
    @Test
    void ca1EncenderClimatizador() {
    	dispositivoConcreto.ejecutar("ENCENDER");
    }
    
    @Test
    void ca2EnviarComandoNoSoportado() {
    	IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () ->
    		dispositivoConcreto.ejecutar("ESTABLECER TEMPERATURA")
    	);
    	assertEquals(IllegalArgumentException.class, excepcion.getClass());
    	assertEquals("Comando no soportado", excepcion.getMessage());
    }

}