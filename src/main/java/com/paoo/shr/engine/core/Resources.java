package com.paoo.shr.engine.core;

import com.paoo.shr.util.designpatterns.lazy.Lazy;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Resources implements RequiresInitialization {

    // Folderul de resurse
    private Path resources = null;
    private List<Path> folders = new ArrayList<>();
    private List<Path> filePaths = new ArrayList<>();

    private Map<String, Lazy<FileInputStream>> textFiles = new HashMap<>();
    private Map<String, Lazy<Image>> images = new HashMap<>();
    private Map<String, Lazy<AudioClip>> sfxFiles = new HashMap<>();
    private Map<String, Lazy<Media>> musicFiles = new HashMap<>();

    // Singleton
    private Resources() { }

    /**
     *
     * @param filename
     * @return FileInputStream-ul corespunzator fisierului .txt cautat, sau null daca fisierul nu a fost gasit
     */
    public static FileInputStream getTextFile(String filename) throws FileNotFoundException {
        return Resources.getInstance().textFiles.get(filename).getValue();
    }

    public static Image getImage(String filename) throws FileNotFoundException {
        return Resources.getInstance().images.get(filename).getValue();
    }

    public static AudioClip getSfx(String filename) throws FileNotFoundException {
        return Resources.getInstance().sfxFiles.get(filename).getValue();
    }

    public static Media getMusic(String filename) throws FileNotFoundException {
        return Resources.getInstance().musicFiles.get(filename).getValue();
    }

    @Override
    public void init() throws Exception, FileNotFoundException {
//        resources = new File(Resources.class.getResource("/res/").toExternalForm());
        resources = Paths.get(this.getClass().getResource("/res/").toURI());

        System.out.println("Folder cu resurse: " + resources + "\n");

        folders = Files.list(resources)
                            .collect(Collectors.toList());

        for (Path dir : folders) {
            filePaths.addAll(
                    Files.list(dir)
                            .collect(Collectors.toList())
            );
        }

        for (Path filePath : filePaths) {
            String fileName = filePath.getFileName().toString();
            String extension = filePath.getFileName().toString().substring(filePath.getFileName().toString().lastIndexOf("."));

            switch (extension) {
                case ".txt": {
                    if (!textFiles.containsKey(fileName)) {
                        textFiles.put(fileName, new Lazy<>( () -> {
                            try {
                                return new FileInputStream(filePath.toFile());
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        }));
                    }
                    break;
                }

                case ".mp3":
                case ".wav": {
                    System.out.println("Lucrez cu fisierul de la pathul " + filePath + '\n' + "fileName: " + fileName + '\n' + "extension: " + extension);

                    if (fileName.startsWith("sfx_")) { // Daca fisierul e sfx, numele tb sa inceapa cu sfx_
                        if (!sfxFiles.containsKey(fileName)) {
                            sfxFiles.put(fileName, new Lazy<> ( () -> {
                                return new AudioClip("res/audio/" +fileName);
                            })); // FIXME: schimba
                        }
                    }

                    if (fileName.startsWith("music_")) { // Daca fisierul e muzica, numele tb sa inceapa cu music_
                        if (!musicFiles.containsKey(fileName)) {
                            musicFiles.put(fileName, new Lazy<> ( () -> {
                                return new Media("res/audio/" +fileName);
                            })); // FIXME: schimba
                        }
                    }
                    break;
                }

                case ".jpg":
                case ".png": {
                    if (!images.containsKey(fileName)) {
                        images.put(fileName, new Lazy<>( () -> {
                            System.out.println("res/sprites/" + fileName);
                            Image img = new Image("res/sprites/" + fileName);

                            if (img == null) {
                                System.out.println("Nu exista imaginea " + fileName);
                                throw new FileNotFoundException(fileName);
                            }

                            return img;
                        }));
                    }
                    break;
                }

                default: {
                    System.out.println(extension + " not supported");
                }
            }
        }
        System.out.println(sfxFiles.size() + " sfx files");
        System.out.println(sfxFiles.keySet() + " " + sfxFiles.values());


        System.out.println(musicFiles.size() + " music files");

    }

    private static final class SingletonHolder { private static final Resources INSTANCE = new Resources(); }
    public static Resources getInstance() { return SingletonHolder.INSTANCE; }
}
