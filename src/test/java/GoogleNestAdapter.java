import mock.TermostatoNestMock;
import org.pp2.Dispositivo;
import org.pp2.DispositivoObserver;

public class GoogleNestAdapter implements Dispositivo {
    DispositivoObserver dispositivoObserver;

    private final TermostatoNestMock termostatoNestMock;

    public GoogleNestAdapter() {
        this.termostatoNestMock = new TermostatoNestMock();
    }

    @Override
    public String getNombre() {
        return "d2";
    }

    @Override
    public void ejecutar(String comando) {
        switch (comando){
            case "APAGAR": termostatoNestMock.apagar();
            case "ENCENDER": termostatoNestMock.encender();
            default: throw new IllegalArgumentException("Comando no soportado");
        }
    }

    @Override
    public void registrarObserver(DispositivoObserver observer) {
        this.dispositivoObserver = observer;
    }

    @Override
    public void eliminarObserver() {
        this.dispositivoObserver = null;
    }
}
