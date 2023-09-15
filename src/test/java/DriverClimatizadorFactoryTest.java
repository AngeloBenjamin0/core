import org.pp2.Dispositivo;
import org.pp2.DriverClimatizador;
import org.pp2.DriverClimatizadorFactory;

public class DriverClimatizadorFactoryTest implements DriverClimatizadorFactory {
    @Override
    public boolean isCompatible(Dispositivo dispositivo) {
        return dispositivo.getModelo().equals("Samsung AC123");
    }

    @Override
    public DriverClimatizador create() {
        return new DriverClimatizadorTest();
    }
}
