package driverClimatizador;

import org.pp2.Dispositivo;
import org.pp2.DriverClimatizador;

public class DriverClimatizadorTest implements DriverClimatizador {

	@Override
	public void enfriar(Dispositivo dispositivo) {
		throw new RuntimeException("Operación no aceptada por Samsung-V6");
	}

	@Override
	public void encender(Dispositivo dispositivo) {
		System.out.println("Encendiendo Samsung-V6");
	}

}
