package dev.notcacha.languagelib.bukkit.loader;

import dev.notcacha.languagelib.bukkit.file.BukkitLanguageFile;
import dev.notcacha.languagelib.exception.FileNotFoundException;
import dev.notcacha.languagelib.file.LanguageFile;
import dev.notcacha.languagelib.i18n.I18n;
import dev.notcacha.languagelib.i18n.message.Message;
import dev.notcacha.languagelib.loader.FileLoader;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;

public class BukkitFileLoader implements FileLoader {

    private final I18n i18n;
    private String format;

    public BukkitFileLoader(I18n i18n) {
        this.i18n = i18n;
        this.format = "language_%lang%.yml";
    }

    @Override
    public FileLoader setFormat(String format) {
        this.format = format;
        return this;
    }

    @Override
    public LanguageFile load(String name, File folder) {
        File file = new File(folder, format.replace("%lang%", name));
        if (!file.exists()) {
            throw new FileNotFoundException(i18n.getMessage(Message.FILE_NOT_FOUND).replace("%file_name%", name));
        }

        return new BukkitLanguageFile(i18n, YamlConfiguration.loadConfiguration(file));
    }

}
