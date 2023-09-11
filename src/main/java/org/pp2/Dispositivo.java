package org.pp2;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dispositivo {
    private String id;
    private String nombre;
    private String modelo;
    private DriverClimatizador driverClimatizador; //FIXME: El dispositivo no debería conocer su Driver
    private Set<String> comandosAceptables; //FIXME El dispositivo no debería conocer los comandos aceptables;


    public void ejecutar(String comando){
        if (!comandosAceptables.contains(comando)) throw new IllegalArgumentException("Comando no aceptado");

        //FIXME: Esto claramente también tiene que ser refactorizado.
        switch (comando){
            case "ENCENDER" : driverClimatizador.encender(this); break;
            case "APAGAR" : driverClimatizador.apagar(this); break;
            default: throw new IllegalArgumentException("El dispositivo no acepta el comando solicitado");
        }

    }
}
