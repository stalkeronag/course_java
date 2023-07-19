package org.example;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Parameters(separators = "=")
public class Main {
    @Parameter(names = {"--folders"})
    private List<String> listFolders;

    public static void main(String[] args) {

        Main main = new Main();
        Config config = new Config();

        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);
        ExecutorService executorService = Executors.newFixedThreadPool(config.getCountThreads());
        int countFolders = main.listFolders.size();

        for (int i = 0; i < countFolders; i++) {
            File folder = new File(main.listFolders.get(i));
            File[] files = folder.listFiles();
            if (files != null) {
                for (int j = 0; j < files.length; j++) {
                    Runnable fileThread = new FileThreadInfo(files[j].getPath(), files[j].getName());
                    executorService.submit(fileThread);
                }
            }

        }
        executorService.shutdown();
    }
}