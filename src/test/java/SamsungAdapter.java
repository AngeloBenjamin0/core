import mock.AireAcondicionadoSamsungMock;
import org.pp2.Dispositivo;

public class SamsungAdapter implements Dispositivo {

    AireAcondicionadoSamsungMock aireAcondicionadoSamsungMock;

    public SamsungAdapter(){
        aireAcondicionadoSamsungMock = new AireAcondicionadoSamsungMock();
    }

    @Override
    public String getNombre() {
        return "Samsung v2";
    }

    @Override
    public void ejecutar(String comando) {
        switch (comando){
            case "APAGAR": aireAcondicionadoSamsungMock.apagar(); break;
            case "ENCENDER": aireAcondicionadoSamsungMock.encender(); break;
            default: throw new IllegalArgumentException("Comando no soportado");
        }
    }
}
