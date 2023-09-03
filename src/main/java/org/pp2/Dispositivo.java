package org.pp2;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dispositivo {
    private String id;
    private String nombre;
    private String modelo;
}
