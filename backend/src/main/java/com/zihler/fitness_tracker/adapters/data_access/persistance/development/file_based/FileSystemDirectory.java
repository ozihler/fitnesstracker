package com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.exceptions.ConfiguringFileSystemFailed;
import com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.exceptions.LoadingDataFromFileSystemFailed;
import com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.exceptions.StoringToFileSystemFailed;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;

public class FileSystemDirectory<T extends Namable> {
    private static final Logger logger = LogManager.getLogger();
    private static final String APP_NAME = "fitness-tracker";

    private Path pathToFolder;
    private Class<T> classToConvertFileInto;

    private FileSystemDirectory(String folderName, Class<T> classToConvertFileInto) {
        createFolderWith(folderName);
        this.classToConvertFileInto = classToConvertFileInto;
    }

    private void createFolderWith(String folderName) {
        this.pathToFolder = pathToFolder(folderName);

        if (Files.exists(pathToFolder)) {
            return;
        }

        try {
            Files.createDirectories(pathToFolder);
        } catch (IOException e) {
            throw new ConfiguringFileSystemFailed(e.getCause());
        }
    }

    private Path pathToFolder(String folderName) {
        Path sourcePath = Paths.get(String.format("%s/%s/%s", System.getProperty("user.home"), APP_NAME, folderName));
        logger.debug("Source path is: " + sourcePath.toAbsolutePath());
        return sourcePath;
    }

    public static <T extends Namable> FileSystemDirectory<T> mkDir(String dirName, Class<T> classToConvertFileInto) {
        return new FileSystemDirectory<T>(dirName, classToConvertFileInto);
    }

    public void store(Namable file) {
        try {
            File rawFile = fileNamed(file.name());
            fileWriter().writeValue(rawFile, file);
            logger.debug("Stored file " + file + " into raw file " + rawFile);
        } catch (IOException e) {
            throw new StoringToFileSystemFailed(e.getCause());
        }
    }

    private File fileNamed(String fileName) {
        return new File(this.pathToFolder.toAbsolutePath() + "/" + fileName);
    }

    public Set<T> fetchAllFilesInDirectory() {
        try {
            return Files.walk(pathToFolder.toAbsolutePath())
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .map(this::read)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new LoadingDataFromFileSystemFailed(e);
        }
    }

    private T read(File file) {
        try {
            return fileWriter().readValue(file, classToConvertFileInto);
        } catch (IOException e) {
            throw new LoadingDataFromFileSystemFailed(e);
        }
    }

    private ObjectMapper fileWriter() {
        ObjectMapper jsonFileWriter = new ObjectMapper();
        jsonFileWriter.configure(SerializationFeature.INDENT_OUTPUT, true);
        return jsonFileWriter;
    }

}
