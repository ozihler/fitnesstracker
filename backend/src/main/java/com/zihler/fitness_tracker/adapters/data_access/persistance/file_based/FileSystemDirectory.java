package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.exceptions.ConfiguringFileSystemFailed;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.exceptions.CouldNotDeleteFolderException;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.exceptions.LoadingDataFromFileSystemFailed;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.exceptions.StoringToFileSystemFailed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileSystemDirectory<T extends Namable> {
    private static final Logger logger = LoggerFactory.getLogger(FileSystemDirectory.class);
    private static final String APP_NAME = "fitness-tracker";

    private Path pathToFolder;
    private final Class<T> classToConvertFileInto;

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
        logger.info("Source path is: " + sourcePath.toAbsolutePath());
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

    public List<T> fetchAllFilesInDirectory() {
        try {
            return Files.walk(pathToFolder.toAbsolutePath())
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .map(this::read)
                    .collect(Collectors.toList());
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

    public void deleteFolder() {
        try {
            Files.walk(pathToFolder.toAbsolutePath())
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .forEach(File::delete);
            Files.deleteIfExists(pathToFolder);
        } catch (IOException e) {
            throw new CouldNotDeleteFolderException(e);
        }
    }
}
