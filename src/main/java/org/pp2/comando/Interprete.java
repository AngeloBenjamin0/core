package org.pp2.comando;

import org.pp2.Dispositivo;

public interface Interprete {
    public void ejecutar(Dispositivo dispositivo, Comando comando);
}
