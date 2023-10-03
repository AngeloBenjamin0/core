package org.pp2;

import lombok.Getter;
import lombok.Setter;
import org.pp2.mock.AireAcondicionadoSamsung;

@Getter
@Setter
public class Dispositivo {
    private String id;
    private String modelo;

    public void ejecutar(String comando){
        AireAcondicionadoSamsung aireAcondicionadoSamsung = new AireAcondicionadoSamsung();

        switch (comando){
            case "APAGAR": aireAcondicionadoSamsung.apagar();
            case "ENCENDER": aireAcondicionadoSamsung.encender();
            default:
                throw new IllegalArgumentException("Comando no soportado");
        }
    }
}
