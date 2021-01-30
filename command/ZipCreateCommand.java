package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ZipFileManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class ZipCreateCommand extends ZipCommand{
    @Override
    public void execute() throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Введете полный путь к архиву");
            ZipFileManager zipFileManager = new ZipFileManager(Paths.get(reader.readLine()));
            System.out.println("Введете путь к файлу, который будем архивировать");
            zipFileManager.createZip(Paths.get(reader.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
