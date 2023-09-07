package org.pp2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DispositivoMapper {
    private final File dispositivoJsonFile;

    public DispositivoMapper(String dispositivoJsonFilePath) {
        this.dispositivoJsonFile = new File(dispositivoJsonFilePath);
    }

    public List<Dispositivo> getDispositivos(){
        try {
            return new ObjectMapper().readValue(dispositivoJsonFile, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
