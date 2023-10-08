package mock;

public class TermostatoNestMock {

    public void ajustarTemperatura(int temperatura) {
        System.out.println("Se ha ajustado la temperatura a " + temperatura + " grados Celsius.");
    }

    public void ajustarVelocidadVentilador(int velocidad) {
        System.out.println("Se ha ajustado la velocidad del ventilador a " + velocidad);
    }

    public void ajustarModo(String modo) {
        System.out.println("Se ha ajustado el modo a " + modo);
    }

    public void encender(){
        System.out.println("Encendiendo...");
    }

    public void apagar(){
        System.out.println("Apagando...");
    }

}
