package org.pp2.registro_comandos;

import java.time.LocalDateTime;

public class ComandoRegistro {
    private String dispositivoNombre;
    private String comando;
    private LocalDateTime timestamp;

    public ComandoRegistro(String dispositivoNombre, String comando, LocalDateTime timestamp) {
        this.dispositivoNombre = dispositivoNombre;
        this.comando = comando;
        this.timestamp = timestamp;
    }

    public String getDispositivoNombre() {
        return dispositivoNombre;
    }

    public String getComando() {
        return comando;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
