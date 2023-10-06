package mock;

public class AireAcondicionadoSamsungMock {

    private boolean encendido;
    private int temperatura;
    private int velocidadVentilador;

    public AireAcondicionadoSamsungMock() {
        // Inicializar el dispositivo de climatización
        encendido = false;
        temperatura = 25; // Temperatura inicial
        velocidadVentilador = 1; // Velocidad del ventilador inicial (por ejemplo, baja)
    }

    public void encender() {
        encendido = true;
        System.out.println("El aire acondicionado se ha encendido.");
    }

    public void apagar() {
        encendido = false;
        System.out.println("El aire acondicionado se ha apagado.");
    }

    public void ajustarTemperatura(int temperatura) {
        this.temperatura = temperatura;
        System.out.println("Se ha ajustado la temperatura a " + temperatura + " grados Celsius.");
    }

    public void ajustarVelocidadVentilador(int velocidad) {
        this.velocidadVentilador = velocidad;
        System.out.println("Se ha ajustado la velocidad del ventilador a " + velocidad);
    }
}