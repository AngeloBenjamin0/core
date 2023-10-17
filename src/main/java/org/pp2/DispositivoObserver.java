package org.pp2;

import java.time.LocalDateTime;

public interface DispositivoObserver {
    void registrarComando(String dispositivoNombre, String comando, LocalDateTime timestamp);
}
