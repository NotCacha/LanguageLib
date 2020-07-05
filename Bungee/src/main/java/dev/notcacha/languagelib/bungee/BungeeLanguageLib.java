package dev.notcacha.languagelib.bungee;

import dev.notcacha.languagelib.LanguageLib;
import dev.notcacha.languagelib.TranslateManager;
import net.md_5.bungee.config.Configuration;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class BungeeLanguageLib<C extends Configuration> implements LanguageLib<C> {

    private final C defaultFile;
    private final Map<String, C> files;
    private final TranslateManager<C> translateManager;

    public BungeeLanguageLib(@NotNull String defaultLanguage, @NotNull C defaultFile) {
        this.files = new HashMap<>();
        this.defaultFile = defaultFile;
        this.files.put(defaultLanguage, defaultFile);
        this.translateManager = new TranslateManagerImplementation<>(this);
    }

    @Override
    public Map<String, C> getFiles() {
        return this.files;
    }

    @Override
    public C getFile(@NotNull String language) {
        return this.files.get(language);
    }

    @Override
    public TranslateManager<C> getTranslateManager() {
        return this.translateManager;
    }

    @Override
    public C getDefaultFile() {
        return this.defaultFile;
    }
}
