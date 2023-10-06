import mock.AireAcondicionadoSamsungMock;
import org.pp2.Dispositivo;
import org.pp2.DriverFactory;

public class SamsungDriverFactory implements DriverFactory {
    @Override
    public boolean isCompatible(Dispositivo dispositivo) {
        return dispositivo.getModelo().contains("Samsung");
    }

    @Override
    public Object create(Dispositivo dispositivo) {
        return new AireAcondicionadoSamsungMock();
    }
}
