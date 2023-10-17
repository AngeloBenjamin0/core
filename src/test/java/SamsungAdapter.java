import mock.AireAcondicionadoSamsungMock;
import org.pp2.Dispositivo;
import org.pp2.DispositivoObserver;

import static java.time.LocalDateTime.now;

public class SamsungAdapter implements Dispositivo {

    AireAcondicionadoSamsungMock aireAcondicionadoSamsungMock;
    DispositivoObserver observer;

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

    @Override
    public void registrarObserver(DispositivoObserver observer) {
        this.observer = observer;
    }

    @Override
    public void eliminarObserver() {

    }
}
