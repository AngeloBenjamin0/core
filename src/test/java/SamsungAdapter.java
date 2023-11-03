import mock.AireAcondicionadoSamsungMock;
import org.pp2.Dispositivo;
import us.RegistroResultadoEjecucion;

public class SamsungAdapter implements Dispositivo {

    private final AireAcondicionadoSamsungMock aireAcondicionadoSamsungMock;

    public SamsungAdapter(){
        aireAcondicionadoSamsungMock = new AireAcondicionadoSamsungMock();

    }

    @Override
    public String getNombre() {
        return "d1";
    }

    @Override
    public void ejecutar(String comando) {
        switch (comando){
            case "APAGAR": {
                aireAcondicionadoSamsungMock.apagar();
                RegistroResultadoEjecucion.addResultadoEjecucion("Se ejecuta comando APAGAR");
            } break;
            case "ENCENDER": {
                aireAcondicionadoSamsungMock.encender();
                RegistroResultadoEjecucion.addResultadoEjecucion("Se ejecuta comando ENCENDER");
            }
            break;
            default: throw new IllegalArgumentException("Comando inexistente");
        }
    }
}
