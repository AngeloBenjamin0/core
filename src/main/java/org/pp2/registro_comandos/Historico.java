package org.pp2.registro_comandos;

import org.pp2.DispositivoObserver;
import org.pp2.registro_comandos.ComandoRegistro;

import java.time.LocalDateTime;
import java.util.*;

public class Historico implements DispositivoObserver {
    private List<ComandoRegistro> registros;

    public Historico(){
        this.registros = new ArrayList<>();
    }

    @Override
    public void registrarComando(String dispositivoNombre, String comando, LocalDateTime timestamp) {
        registros.add(new ComandoRegistro(dispositivoNombre, comando, timestamp));
    }

    public List<ComandoRegistro> getRegistros() {
        return registros;
    }
}
