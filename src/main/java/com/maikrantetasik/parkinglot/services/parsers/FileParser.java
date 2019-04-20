package com.maikrantetasik.parkinglot.services.parsers;

import java.io.IOException;

public interface FileParser {
    String[] parse(String filePath) throws IOException;
}

