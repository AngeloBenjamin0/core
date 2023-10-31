package org.pp2;

public class DispositivoDiscoveringException extends RuntimeException{

    public DispositivoDiscoveringException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public DispositivoDiscoveringException(String mensaje) {
        super(mensaje);
    }
}
