package com.example;

// Exercise 3: reads content via FileReader, processes it, and (in a real
// implementation) could persist the result via FileWriter.
public class FileService {

    private final FileReader fileReader;
    private final FileWriter fileWriter;

    public FileService(FileReader fileReader, FileWriter fileWriter) {
        this.fileReader = fileReader;
        this.fileWriter = fileWriter;
    }

    public String processFile() {
        String content = fileReader.read();
        String processed = "Processed " + content;
        fileWriter.write(processed);
        return processed;
    }
}
