package org.example;

import java.io.File;

public class FileThreadInfo extends Thread {

    private final String path;

    private final String name;

    public FileThreadInfo(String path, String name) {
        this.path = path;
        this.name = name;
    }

    @Override
    public void run() {
        File file = new File(path);
        System.out.println(Thread.currentThread().getName() + " " + name + " " + " " + file.length());
    }
}
