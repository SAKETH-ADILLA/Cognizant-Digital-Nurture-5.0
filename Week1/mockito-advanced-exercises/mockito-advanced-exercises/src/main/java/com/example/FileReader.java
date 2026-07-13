package com.example;

// Exercise 3: Mocking File I/O.
// A custom abstraction over reading file content (deliberately NOT
// java.io.FileReader, so it's a simple interface that's trivial to mock -
// no real file system access needed in tests).
public interface FileReader {
    String read();
}
