package org.pp2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.toString(args));
        List<Dispositivo> dispositivos = new ObjectMapper().readValue(
                new File(args[0]),
                new TypeReference<>() {
                }
        );
        System.out.println(dispositivos);
    }
}