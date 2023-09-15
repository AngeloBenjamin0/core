package us;

import org.junit.jupiter.api.Test;
import org.pp2.ClimaTotal;
import org.pp2.Dispositivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;



import org.junit.jupiter.api.BeforeEach;

public class UserStory1Test {
	
	private String dispositivoJsonPath;
	private List<Dispositivo> dispositivos;
	private Dispositivo dispositivo;
	
	@BeforeEach
	void setUp() {
		dispositivoJsonPath = "src/test/resources/dispositivos.json"; 
		dispositivos = ClimaTotal.inicializarDispositivos(dispositivoJsonPath);
		dispositivo = dispositivos.get(0);
		String discovererPath = "src/test/resources";
	}
    
//    @Test
//    void encenderClimatizador() {
//    	dispositivo.ejecutar("ENCENDER");
//    }
    
    @Test
    void enviarComandoNoSoportado() {
    	IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () ->
    		dispositivo.ejecutar("ESTABLECER TEMPERATURA")
    	);
    	assertEquals(IllegalArgumentException.class, excepcion.getClass());
    	assertEquals("Comando no soportado", excepcion.getMessage());
    }
    
//    @Test
//    void comandoNoSoportadoPorClimatizador() {
//    	Exception excepcion = assertThrows(RuntimeException.class, () ->
//    		dispositivo.ejecutar("ENFRIAR")
//    	);
//    	assertEquals(RuntimeException.class, excepcion.getClass());
//    	assertEquals("Operación no aceptada por Samsung-V6", excepcion.getMessage());
//    }

}