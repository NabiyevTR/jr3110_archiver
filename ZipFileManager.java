package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileManager {
    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) throws Exception {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile))) {

            if (!Files.exists(zipFile.getParent())) {
                Files.createDirectory(zipFile.getParent());
            }
            if (Files.isRegularFile(source)) {
                addNewZipEntry(zipOutputStream, source.getParent(), source.getFileName());
            } else if (Files.isDirectory(source)) {
                FileManager fileManager = new FileManager(source);
                List<Path> fileList = fileManager.getFileList();
                for (Path path : fileList) {
                    addNewZipEntry(zipOutputStream, source, path);
                }
            } else {
                throw new PathIsNotFoundException();
            }


            // InputStream inputStream = Files.newInputStream(source)) {
        }
    }

    private void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName) throws Exception {

        try (InputStream inputStream = new FileInputStream(filePath.toString() + fileName.toString())) {
            ZipEntry zipEntry = new ZipEntry(fileName.toString());
            zipOutputStream.putNextEntry(zipEntry);
            copyData(inputStream, zipOutputStream);
        }
    }

    private void copyData(InputStream in, OutputStream out) throws Exception {
        while (in.available() > 0) {
            out.write(in.read());
        }
    }
}
