package org.pp2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class EspecificacionParser {
    private final static TypeReference<Map<String, List<String>>> TYPE_REFERENCE = new TypeReference<>() {};
    private final File especificacionDispositivos;

    public EspecificacionParser(File especificacionDispositivos) throws FileNotFoundException {
        this.especificacionDispositivos = especificacionDispositivos;
        if (!this.especificacionDispositivos.exists())
            throw new FileNotFoundException();
    }

    public Map<String, List<String>> parse(){
        Map<String, List<String>> nombreDispositivosMap;
        try {
            nombreDispositivosMap = new ObjectMapper().readValue(especificacionDispositivos, TYPE_REFERENCE);
        } catch (MismatchedInputException e) {
            throw new RuntimeException("Formato de especificación inválido", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return nombreDispositivosMap;
    }

}
