package org.pp2;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dispositivo {
    private String id;
    private String modelo;
    private ClimatizadorGateway climatizadorGateway;

    public void ejecutar(String comando){

        switch (comando){
            case "APAGAR": climatizadorGateway.apagar();
            case "ENCENDER": climatizadorGateway.encender();
            default:
                throw new IllegalArgumentException("Comando no soportado");
        }
    }
}
