import org.pp2.Dispositivo;
import org.pp2.DriverClimatizador;
import org.pp2.DriverClimatizadorFactory;

public class OtroDriverClimatizadorFactoryTest implements DriverClimatizadorFactory {
    @Override
    public boolean isCompatible(Dispositivo dispositivo) {
        return false;
    }

    @Override
    public DriverClimatizador create() {
        return null;
    }
}
