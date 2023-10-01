package org.pp2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pp2.comando.Interprete;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dispositivo {
    private String id;
    private String nombre;
    private String modelo;
    private Interprete interprete;

    public void ejecutar(String comando){
        interprete.interpretar(comando);
    }
}
