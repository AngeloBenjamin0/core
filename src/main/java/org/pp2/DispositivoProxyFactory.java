package org.pp2;

import org.pp2.time.LocalTimeService;
import org.pp2.time.ProdLocalTimeService;
import org.pp2.time.TestLocalTimeService;

import java.io.*;
import java.util.Properties;

public class DispositivoProxyFactory {

    private final static String PROD_ENV = "PROD";
    private final Properties properties;

    public DispositivoProxyFactory(String configuracionHorariaPath) throws FileNotFoundException {
        File file = new File(configuracionHorariaPath);
        if (!file.exists()) throw new FileNotFoundException();
        properties = new Properties();
        try {
            properties.load(new BufferedReader(new FileReader(file)));
        } catch (IOException e) {
            // TODO: Usar java.util.logging.Logger
            System.out.println("Warning! No se pueden cargar las configuraciones de horarios de exclusión de comandos");
        }
    }

    public Dispositivo crear(Dispositivo dispositivo) {
        if (properties == null) return dispositivo;

        // FIXME: Programar caso donde horarioInicio == null o horarioFin == null
        int horarioInicio = Integer.parseInt((String) properties.get("restriccion.horario.hora.inicio"));
        int horarioFin = Integer.parseInt((String) properties.get("restriccion.horario.hora.fin"));

        return new DispositivoProxy(dispositivo, horarioInicio, horarioFin, getLocalTimeService());
    }

    private LocalTimeService getLocalTimeService(){
        // FIXME: La lógica de determinar qué LocalTimeService utilizar NO debería estar acá.
        // Una opción es, al igual que los Dispositivos, utilizar un Plugin y cargar la clase
        // a utilizar en runtime.
        String env = (String) properties.get("env");
        if (PROD_ENV.equals(env))
            return new ProdLocalTimeService();

        return new TestLocalTimeService();
    }

}
