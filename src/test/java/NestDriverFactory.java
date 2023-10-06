import mock.TermostatoNestMock;
import org.pp2.Dispositivo;
import org.pp2.DriverFactory;

public class NestDriverFactory implements DriverFactory {
    @Override
    public boolean isCompatible(Dispositivo dispositivo) {
        return dispositivo.getModelo().contains("NEST");
    }

    @Override
    public Object create(Dispositivo dispositivo) {
        return new TermostatoNestMock();
    }
}
