import org.pp2.ComandoDispositivo;
import org.pp2.ComandoDispositivoFactory;
import org.pp2.Dispositivo;

public class OtroDriverClimatizadorFactoryTest implements ComandoDispositivoFactory {
    @Override
    public boolean isCompatible(Dispositivo dispositivo) {
        return false;
    }

    @Override
    public ComandoDispositivo create() {
        return null;
    }
}
