import org.interfaces.ComandoDispositivo;
import org.interfaces.ComandoDispositivoFactory;
import org.interfaces.Dispositivo;

public class ComandoDispositivoFactoryTest implements ComandoDispositivoFactory {
    @Override
    public boolean isCompatible(Dispositivo dispositivo) {
        return true;
    }

    @Override
    public ComandoDispositivo create() {
        return new ComandoDispositivoTest();
    }
}
