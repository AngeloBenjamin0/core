import org.pp2.ComandoDispositivo;
import org.pp2.ComandoDispositivoFactory;
import org.pp2.Dispositivo;

public class DriverClimatizadorFactoryTest implements ComandoDispositivoFactory {
    @Override
    public boolean isCompatible(Dispositivo dispositivo) {
        return dispositivo.getModelo().equals("Samsung AC123");
    }

    @Override
    public ComandoDispositivo create() {
        return new DriverClimatizadorTest();
    }
}
