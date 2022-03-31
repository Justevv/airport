package com.sber.airport.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Reader {
    private static final Logger LOGGER = LogManager.getLogger(Reader.class);
    @Value("${file.path}")
    private String file;

    public List<String> readFile() {
        List<String> lines = new ArrayList<>();
        try (InputStream in = getClass().getResourceAsStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            lines = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("Error read file", e);
        }
        LOGGER.debug("Current data from file {}", lines);
        return lines;
    }
}
