package org.pp2;

import lombok.Getter;
import lombok.Setter;
import org.interfaces.Dispositivo;
import org.pp2.comando.Interprete;

@Getter
@Setter
public class DispositivoConcreto extends Dispositivo {
    private String id;
    private String nombre;
    private String modelo;
    private Interprete interprete;

    public DispositivoConcreto(){
        super();
    }

    @Override
    public void ejecutar(String comando){
        interprete.interpretar(comando);
    }
}
