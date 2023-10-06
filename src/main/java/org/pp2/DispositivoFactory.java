package org.pp2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DispositivoFactory {
    private final File dispositivoJsonFile;

    public DispositivoFactory(String dispositivoJsonFilePath) {
        this.dispositivoJsonFile = new File(dispositivoJsonFilePath);
    }

    public List<DispositivoAdapter> getDispositivos(){
        try {
            return new ObjectMapper().readValue(dispositivoJsonFile, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
