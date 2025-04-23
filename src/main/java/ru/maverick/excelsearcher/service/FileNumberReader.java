package ru.maverick.excelsearcher.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface FileNumberReader {
    List<Integer> readNumbers(String pathFile, int n) throws IOException;
}
