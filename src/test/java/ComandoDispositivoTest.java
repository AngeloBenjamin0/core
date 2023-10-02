import org.interfaces.ComandoDispositivo;

public class ComandoDispositivoTest implements ComandoDispositivo {
	@Override
	public void ejecutar() {
		System.out.println("Encendiendo Samsung-V6");
	}

	@Override
	public String getNombreComando() {
		return "ENCENDER";
	}
}
