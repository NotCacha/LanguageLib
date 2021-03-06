package dev.notcacha.languagelib.manageable;

import dev.notcacha.languagelib.file.LanguageFile;
import dev.notcacha.languagelib.loader.FileLoader;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SimpleFileManageable  implements FileManageable {

    private final FileLoader fileLoader;
    private final File folder;

    private final Map<String, LanguageFile> fileMap;
    private final String defaultFile;

    public SimpleFileManageable(FileLoader fileLoader, File folder, String defaultLanguage) {
        this(fileLoader, folder, defaultLanguage, false);
    }

    public SimpleFileManageable(FileLoader fileLoader, File folder, String defaultLanguage, boolean createFile) {
        this.fileLoader = fileLoader;
        this.folder = folder;

        this.defaultFile = defaultLanguage;
        this.fileMap = new HashMap<>();

        add(defaultLanguage, createFile);
    }

    @Override
    public Set<LanguageFile> get() {
        return new HashSet<>(this.fileMap.values());
    }

    @Override
    public void add(String key, boolean create) {
        this.fileMap.put(key, (!create) ? fileLoader.loadAndCreate(key, folder) : fileLoader.loadAndCreate(key, folder));
    }

    @Override
    public LanguageFile find(String key) {
        return this.fileMap.get(key);
    }

    @Override
    public boolean exists(String key) {
        return this.fileMap.containsKey(key);
    }

    @Override
    public void remove(String key) {
        this.fileMap.remove(key);
    }

    @Override
    public LanguageFile getDefault() {
        return this.fileMap.get(defaultFile);
    }

}
