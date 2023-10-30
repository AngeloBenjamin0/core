package org.pp2;

public class DispositivoDiscoveringException extends RuntimeException{

    public DispositivoDiscoveringException(String fileName, Throwable cause) {
        super(String.format("No se pudo cargar la extensión con nombre %s", fileName), cause);
    }

}
