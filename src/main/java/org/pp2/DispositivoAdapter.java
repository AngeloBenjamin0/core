package org.pp2;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class DispositivoAdapter extends Dispositivo{
    private String id;
    private String modelo;
    private Map<String, Runnable> comandos;

    public void ejecutar(String nombreComando){
        Runnable comandoDispositivo = comandos.get(nombreComando);
        if (comandoDispositivo == null)
            throw new IllegalArgumentException("Comando no soportado");

        comandoDispositivo.run();
    }
}
