import org.pp2.Dispositivo;

public class DispositivoLoggerProxy implements Dispositivo {

    private final Dispositivo dispositivo;

    public DispositivoLoggerProxy(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    @Override
    public String getNombre() {
        return dispositivo.getNombre();
    }

    @Override
    public void ejecutar(String comando) {
        String log = String.format("INFO - Se ejecuta comando %s en dispositivo %s", comando, this.getNombre());
        System.out.println(log);
        RegistroEjecucionComando.addEjecucionComando(log);

        dispositivo.ejecutar(comando);
    }
}
