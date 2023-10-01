import org.pp2.ComandoDispositivo;

public class DriverClimatizadorTest implements ComandoDispositivo {
	@Override
	public void ejecutar() {
		System.out.println("Encendiendo Samsung-V6");
	}

	@Override
	public String getNombreComando() {
		return "ENCENDER";
	}
}
