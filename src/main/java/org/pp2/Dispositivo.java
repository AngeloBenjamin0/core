package org.pp2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // Necesario para poder serializar/deserializar.
@AllArgsConstructor
public abstract class Dispositivo {

    private String id;
    private String modelo;
    private String nombre;

    public abstract void ejecutar(String comando);
}
