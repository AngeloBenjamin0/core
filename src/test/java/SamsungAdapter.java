import mock.AireAcondicionadoSamsungMock;
import org.pp2.Dispositivo;

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
                RegistroEjecucionComando.addEjecucionComando("Se ejecuta comando APAGAR");
            } break;
            case "ENCENDER": {
                aireAcondicionadoSamsungMock.encender();
                RegistroEjecucionComando.addEjecucionComando("Se ejecuta comando ENCENDER");
            }
            break;
            default: throw new IllegalArgumentException("Comando inexistente");
        }
    }
}
