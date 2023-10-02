import org.interfaces.ComandoDispositivo;
import org.interfaces.ComandoDispositivoFactory;
import org.interfaces.Dispositivo;

public class OtroComandoDispositivoFactoryTest implements ComandoDispositivoFactory {
    @Override
    public boolean isCompatible(Dispositivo dispositivo) {
        return false;
    }

    @Override
    public ComandoDispositivo create() {
        return null;
    }
}
