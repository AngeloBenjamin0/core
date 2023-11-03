package org.pp2;

public class DiscovererException extends RuntimeException{

    public DiscovererException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
