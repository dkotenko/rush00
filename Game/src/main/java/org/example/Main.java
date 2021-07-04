package org.example;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Parameters(separators = "=")
public class Main {
    @Parameter(names="--enemiesCount", required = true)
    int enemiesCount;
    @Parameter(names="--wallsCount", required = true)
    int wallsCount;
    @Parameter(names="--size", required = true)
    int size;
    @Parameter(names="--profile", required = true)
    String profile;


    public static void main(String[] args) {
        Main main = new Main();
        JCommander jCommander = new JCommander(main);
        jCommander.parse(args);
        if (main.enemiesCount + main.wallsCount + 2 > main.size * main.size)
            throw new IllegalArgumentException();
        Map map = new Map(main.size, main.wallsCount, main.enemiesCount);
        map.printMap();
        System.out.printf("--enemiesCount=%d --wallsCount=%d --size=%d ==profile=%s%n", main.enemiesCount, main.wallsCount, main.size, main.profile);
    }
}
